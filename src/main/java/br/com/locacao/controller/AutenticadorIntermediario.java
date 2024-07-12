package br.com.locacao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.locacao.persistencia.entidade.Usuario;
import br.com.locacao.persistencia.jdbc.UsuarioDAO;

@WebServlet(name = "autenticadorIntermediario.do", urlPatterns={"/redirecionamento/*"})
public class AutenticadorIntermediario extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession sessao = req.getSession(false);
		
		if(sessao != null) {
			sessao.invalidate();
		}
		
		resp.sendRedirect("Entrada.html");
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		System.out.println("chamou a verificação pelo post!");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuAutenticado = usuarioDAO.autenticar(usuario);
		
		if(usuAutenticado!=null && usuAutenticado.getRole().equals("adm")) {
			HttpSession sessao = req.getSession(true);
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			Usuario usu = (Usuario)req.getSession().getAttribute("usuAutenticado");
			System.out.println(usu.getRole());
			sessao.setMaxInactiveInterval(60*5);

			req.getRequestDispatcher("IndexADM").forward(req, resp);
			//req.getRequestDispatcher("WEB-INF/adm/index.jsp").forward(req, resp);
		}
		else if(usuAutenticado!=null && usuAutenticado.getRole().equals("usu")) {
			HttpSession sessao = req.getSession(true);
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			Usuario usu = (Usuario)req.getSession().getAttribute("usuAutenticado");
			System.out.println(usu.getRole());
			sessao.setMaxInactiveInterval(60*5);
			//MUDAR ISSO AQUI DPS
			//resp.getWriter().print("<script> window.alert('Usuário não administrador!'); location.href='Entrada.html';</script>");
			req.getRequestDispatcher("IndexUsu").forward(req, resp);
		}
		else{
			resp.getWriter().print("<script> window.alert('Não encontrado!'); location.href='Entrada.html';</script>");
		}
		
		}
}
