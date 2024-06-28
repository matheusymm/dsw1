package br.com.locacao.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.locacao.persistencia.entidade.Usuario;
import br.com.locacao.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/fabricaweb/usucontroller.do
@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet {
	public UsuarioController() {
		System.out.println("Construtor..");
	}
	
	public void init() throws ServletException {
		System.out.println("Init..");
		super.init();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		resp.setContentType("text/html");//configura o response para entender que não só strings, mas html
		String acao = req.getParameter("acao");
		if(acao.equals("exc")) {
			String cpf = req.getParameter("cpf");
			Usuario usu = new Usuario();
			if(cpf != null)
				usu.setCPF(cpf);
		
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usu);
		
			resp.getWriter().print("<b>Excluído com sucesso!<b>");
			System.out.println("Excluído com sucesso");
			resp.sendRedirect("usucontroller.do?acao=lis");
			
		}else if(acao.equals("lis")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = usuarioDAO.buscarTodos();
			
			req.setAttribute("lista", lista);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");//constroi uma instacia do objeto requestDispartcher para realizaar forager
			dispatcher.forward(req, resp);
		
		}else if(acao.equals("alt")){
			String cpf = req.getParameter("cpf");
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.buscarPorCPF(cpf);
			req.setAttribute("usu", usuario);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
		
		}else if(acao.equals("cad")) {
			
			Usuario usuario = new Usuario();
			usuario.setCPF(null);
			usuario.setNome("");
			usuario.setLogin("");
			usuario.setSenha("");
			usuario.setSexo("");
			usuario.setTelefone("");
			
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formusuario.jsp");
			dispatcher.forward(req, resp);
			
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String CPF = req.getParameter("CPF");
		String sexo = req.getParameter("sexo");
		String telefone = req.getParameter("telefone");
		//String datnas = req.getParameter("datnas");
		
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		usu.setCPF(CPF);
		usu.setSexo(sexo);
		//usu.setDatNas(Date.valueOf(datnas));
		usu.setTelefone(telefone);
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usu);
		
		resp.getWriter().print("<b>será?!<b>");
		resp.sendRedirect("usucontroller.do?acao=lis");
		
	}
	
	public void destroy() {
		System.out.println("Destroy..");
		super.destroy();
	}
}
