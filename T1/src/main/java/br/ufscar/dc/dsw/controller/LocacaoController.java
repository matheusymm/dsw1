package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;

@WebServlet(urlPatterns = "/locacoes/*")
public class LocacaoController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private LocacaoDAO dao;

    @Override
    public void init() {
        dao = new LocacaoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {   
    	System.out.println("chamou do get");
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
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaLocacoes", dao.getAll());
        request.getRequestDispatcher("/logado/locacao/lista.jsp").forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/logado/locacao/formCadastro.jsp").forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        LocalDateTime dataLocacao = LocalDateTime.now();
        Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
        dao.insert(locacao);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        LocalDateTime dataLocacao = LocalDateTime.now();
        Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
        dao.delete(locacao);
        response.sendRedirect("lista");
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        String dataLocacaoStr = request.getParameter("dataLocacao");
        
        LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);                                            
        
        Locacao locacao = new Locacao( cpfCliente, cnpjLocadora, dataLocacao);
        request.setAttribute("locacao", locacao);
        request.getRequestDispatcher("/logado/locacao/formEdicao.jsp").forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        LocalDateTime dataLocacao = LocalDateTime.now();
        Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
        dao.update(locacao);
        response.sendRedirect("lista");
    }
}
