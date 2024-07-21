package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet("/loginCliente")
public class ClienteLoginController extends HttpServlet {
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
                ClienteDAO dao = new ClienteDAO();
                Cliente cliente = dao.getByEmail(email);
                if (cliente != null) {
                    if (cliente.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("clienteLogado", cliente);
                        if (cliente.getPapel().equals("admin")) {
                            response.sendRedirect(request.getContextPath() + "/adminsCliente");
                        } else {
                            response.sendRedirect(request.getContextPath() + "/clientes");
                        }
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Cliente não encontrado!");
                }
            }
        } else if (request.getParameter("bReg") != null) {
            response.sendRedirect("registro/registraCliente.jsp");
            return;
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "/login/loginCliente.jsp";
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
