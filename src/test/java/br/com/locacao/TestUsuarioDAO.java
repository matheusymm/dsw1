package br.com.locacao;

import java.util.List;

import br.com.locacao.persistencia.entidade.Usuario;
import br.com.locacao.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {
	
	public static void main(String[] args) {
		testBuscarTodos();
		//testBuscarPorId();
		//testSalvar();
		//testExcluir();
	}
	public static void testAlterar() {
		Usuario usu = new Usuario();
		usu.setId(4);
		usu.setNome("Jãozão da Silva");
		usu.setLogin("jzaoss");
		usu.setSenha("1235678");
				
		//Cadastrando usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		System.out.println("Alterado com sucesso!");
		
	}
	
	public static void testCadastrar() {
		//Criando o usuario
		Usuario usu = new Usuario();
		usu.setNome("Jãozão");
		usu.setLogin("jzão");
		usu.setSenha("123");
				
		//Cadastrando usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
				
		System.out.println("Cadastrado com sucesso!!!");			
	}
	
	public static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setId(4);
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso com sucesso");
	}
	public static void testSalvar() {
		Usuario usuario = new Usuario();
		//usuario.setId(1);
		usuario.setNome("Virmerson");
		usuario.setLogin("vir");
		usuario.setSenha("123456");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
		
		System.out.println("Salvo com sucesso!");
		
	}
	public static void testBuscarPorId() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(1);
		System.out.println(usuario);
	}
	public static void testBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		for(Usuario u: lista) {
			System.out.println(u);
		}
	}
}
