package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;

import br.ufscar.dc.dsw.util.Email;

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
                case "/insercao":
                    insereLocacao(request, response);
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
        List<Locacao> listaLocacoes = daoLocacao.getAll();
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

        if(daoLocacao.existeConflito(cpfCliente, cnpjLocadora, dataLocacaoStr)) {
            // TODO: apresentar erro na tela
            request.setAttribute("erro", "Já existe uma locação para este cliente nesta data");
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
}
