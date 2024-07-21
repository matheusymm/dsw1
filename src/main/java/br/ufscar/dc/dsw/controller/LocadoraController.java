package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Locacao;

@WebServlet(urlPatterns = "/locadoras/*")
public class LocadoraController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private LocadoraDAO dao;
    private LocacaoDAO daoLocacao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
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
                case "/listaLocadoras":
                    listaLocadoras(request, response);
                    break;
                case "/buscaCidade":
                    listaLocadorasCidade(request, response);
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/listaLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaParaCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocadorasCidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidade = request.getParameter("cidade");
        List<Locadora> listaLocadoras = dao.getByCidade(cidade);
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaParaCliente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/formularioLocadora.jsp");
        dispatcher.forward(request, response);
    }
}
