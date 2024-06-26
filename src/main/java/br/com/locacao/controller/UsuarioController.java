package br.com.locacao.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.locacao.persistencia.entidade.Usuario;
import br.com.locacao.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/fabricaweb/cadastro.do
@WebServlet("/cadastro.do")
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
	
		System.out.println("Chamou Get");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String CPF = req.getParameter("CPF");
		String sexo = req.getParameter("sexo");
		//String datnas = req.getParameter("datnas");
		String telefone = req.getParameter("telefone");
		
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		usu.setCPF(CPF);
		usu.setSexo(sexo);
		//usu.setDatNas(Date.valueOf(datnas));
		usu.setTelefone(telefone);
		
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastrar(usu);
		
		resp.getWriter().print("<b>sucesso!<b>");
		
	}
	
	public void destroy() {
		System.out.println("Destroy..");
		super.destroy();
	}
}
