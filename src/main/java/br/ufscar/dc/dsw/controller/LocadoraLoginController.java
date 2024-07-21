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

@WebServlet("/loginLocadora")
public class LocadoraLoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Erro erros = new Erro();

        if (request.getParameter("bIn") != null) {
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            if (email == null || email.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if (!erros.isExisteErros()) {
                LocadoraDAO dao = new LocadoraDAO();
                Locadora locadora = dao.getByEmail(email);
                if (locadora != null) {
                    if (locadora.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("locadoraLogada", locadora);
                        if (locadora.getPapel().equals("admin")) {
                            response.sendRedirect(request.getContextPath() + "/adminsLocadora");
                        } else {
                            response.sendRedirect(request.getContextPath() + "/locadoras");
                        }
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Locadora não encontrada!");
                }
            }
        } else if (request.getParameter("bReg") != null) {
            response.sendRedirect(request.getContextPath() + "/locadoras/novo");
            return;
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "/login/loginLocadora.jsp";
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
