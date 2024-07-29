package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;

import br.ufscar.dc.dsw.util.Email;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/clientes/*")
public class ClienteController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ClienteDAO dao;
    private LocadoraDAO daoLocadora;
    private LocacaoDAO daoLocacao;

    @Override
    public void init() {
        dao = new ClienteDAO();
        daoLocadora = new LocadoraDAO();
        daoLocacao = new LocacaoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercaoLoc":
                    insereLocacao(request, response);
                    break;
                case "/novo":
                    apresentaFormRegistro(request, response);
                    break;
                case "/insercaoCli":
                    insereCliente(request, response);
                    break;
                default:
                    listaLocacoes(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaLocacoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Locacao> listaLocacoes = daoLocacao.getByCpf(((Cliente) session.getAttribute("clienteLogado")).getCpf());
        request.setAttribute("listaLocacoes", listaLocacoes);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/listaCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cpfCliente = ((Cliente) session.getAttribute("clienteLogado")).getCpf();
        List<Locadora> listaLocadoras = daoLocadora.getAll();
        request.setAttribute("cpfCliente", cpfCliente);
        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/formulario.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insereLocacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        String dataLocacaoStr = request.getParameter("dataLocacao");
        LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);
        Erro erros = new Erro();

        Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
        Boolean inseriu = daoLocacao.insert(locacao);

        if(inseriu){
            Cliente cliente = dao.getByCPF(cpfCliente);
            Locadora locadora = daoLocadora.getByCNPJ(cnpjLocadora);
            String assunto = "Nova Locação de Bicicleta";
            String mensagem = "Uma nova locação foi realizada por " + cliente.getNome() + " para a locadora " + locadora.getNome() + " no dia " + dataLocacaoStr;
            Email.sendEmail(cliente.getEmail(), assunto, mensagem);
            Email.sendEmail(locadora.getEmail(), assunto, mensagem);
        } else {
            erros.add("Já existe uma locação nesta data");
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
            return;
        }

        response.sendRedirect("lista");
    }

    private void apresentaFormRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registro/registraCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void insereCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
        String papel = "user";

        Cliente cliente = new Cliente(email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);

        dao.insert(cliente);
        
        response.sendRedirect(request.getContextPath() + "/loginCliente");
    }
}
