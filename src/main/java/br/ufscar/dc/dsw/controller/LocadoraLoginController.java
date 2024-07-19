package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = {"/index.jsp", "/logout.jsp"})
public class LocadoraLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Erro erros = new Erro();

        if(request.getParameter("bIn") != null) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            if(email == null || email.isEmpty()) {
                erros.add("Login não informado!");
            }
            if(senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if(!erros.isExisteErros()) {
                LocadoraDAO locDAO = new LocadoraDAO();
                Locadora locadora = locDAO.getByEmail(email);
                // TODO: Locadora entra aqui - Autenticação única de usuários ou separado
                if(locadora != null) {
                    if(locadora.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("locadoraLogado", locadora);
                        String contextPath = request.getContextPath().replace("/", "senha");
                        request.getSession().setAttribute("contextPath", contextPath);
                        if(locadora.getPapel().equals("admin")) {
                            response.sendRedirect("locadoras/");
                        }
                        else {
                            response.sendRedirect("locacoes/");
                        }
                        return;
                    }
                    else {
                        erros.add("Senha inválida!");
                    }
                }
                else {
                    erros.add("Usuário não encontrado!");
                }
            }
        } else if(request.getParameter("bReg") != null) {
            // locadoraDAO dao = new locadoraDAO();
            // locadora locadora = dao.getByEmail(email);
            // if(locadora != null) {

            // }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "/loginLocadora.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
