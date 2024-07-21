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
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteLogado");

        if(cliente == null) {
            response.sendRedirect(request.getContextPath() + "/loginCliente");
            return;
        } 
                
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
                case "/registro":
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
        List<Locadora> listaLocadoras = daoLocadora.getAll();
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

        if(daoLocacao.existeConflito(cpfCliente, cnpjLocadora, dataLocacaoStr)) {
            // TODO: apresentar erro na tela
            erros.add("Já existe uma locação nesta data");
            apresentaFormCadastro(request, response);
            return;
        }

        Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
        daoLocacao.insert(locacao);

        try {
            Cliente cliente = dao.getByCPF(cpfCliente);
            Locadora locadora = daoLocadora.getByCNPJ(cnpjLocadora);
            String assunto = "Nova Locação de Bicicleta";
            String mensagem = "Uma nova locação foi realizada por " + cliente.getNome() + " para a locadora " + locadora.getNome() + " no dia " + dataLocacaoStr;
            Email.sendEmail(assunto, cliente.getEmail(), mensagem);
            Email.sendEmail(assunto, locadora.getEmail(), mensagem);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Erro ao enviar e-mail: " + e.getMessage());
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
        String cpf = request.getParameter("cpf");
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
        String papel = "user";

        Cliente cliente = new Cliente(email, senha, cpf, nome, telefone, sexo, dataNascimento, papel);

        dao.insert(cliente);
        
        response.sendRedirect(request.getContextPath() + "/loginCliente");
    }
}
