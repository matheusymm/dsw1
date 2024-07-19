package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeController.do", urlPatterns={"/"})
public class HomeController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrei 1 foda-se!!!!");
        String action = request.getPathInfo();
        System.out.println("valor de action: " + action);

        RequestDispatcher rd;
        System.out.println("Entrei 2 foda-se!!!!");
        

        if (action == null) {
            System.out.println("Entrei no if !!foda-se!!!!");
            action = "/clientes";
        }

        try {
            switch (action) {
                case "/clientes":
                    request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
                    

                    rd = request.getRequestDispatcher("/webapp/login/loginUsuario.jsp");
                    rd.forward(request, response);
                    break;
                case "/locadoras":

                    rd = request.getRequestDispatcher("/login/loginUsuario.jsp");
                    rd.forward(request, response);
                    break;
                case "/locacoes":

                    rd = request.getRequestDispatcher("/login/loginUsuario.jsp");
                    rd.forward(request, response);
                    break;
                default:
                    System.out.println("Fudeu fdp!");
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called"); // Log method entry
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
        System.out.println("doGet called"); // Log method entry            
        processRequest(request, response);
    }

}
