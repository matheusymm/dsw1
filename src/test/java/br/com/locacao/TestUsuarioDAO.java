package br.com.locacao;

import java.sql.Date;
import java.util.List;

import br.com.locacao.persistencia.entidade.Usuario;
import br.com.locacao.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {
	
	public static void main(String[] args) {
		System.out.println("TESTE CADASTRAR");
		testCadastrar();
		testBuscarTodos();
		System.out.println("TESTE EXCLUIR");
		testExcluir();
		testBuscarTodos();
		System.out.println("TESTE BUSCAR PELO CPF");
		testBuscarPorCPF();	
	}
	
	public static void testAlterar() {
		Usuario usu = new Usuario();
		//usu.setId(4);
		usu.setNome("Jãozão da Silva");
		usu.setLogin("jzaoss");
		usu.setSenha("1235678");
				
		//Cadastrando usuário no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		System.out.println("Alterado com sucesso!");
		
	}
	
	public static void testCadastrar() {
		Usuario usu = new Usuario();
		usu.setNome("Jãozão da Silva");
		usu.setLogin("Jaozao@ufscar");
		usu.setSenha("1235678");
		usu.setCPF("12345678911");
		
		
		Usuario usu1 = new Usuario();
		usu1.setNome("Giovanni Rossi");
		usu1.setLogin("giovanni@ufscar");
		usu1.setSenha("1235678");
		usu1.setCPF("12345678912");
		usu1.setDatNas(Date.valueOf("2002-06-10"));
		
		Usuario usu2 = new Usuario();
		usu2.setNome("Andre Rettondini");
		usu2.setLogin("andre@ufscar");
		usu2.setSenha("1235678");
		usu2.setCPF("54368495422");
		
		Usuario usu3 = new Usuario();
		usu3.setNome("Matheus Yuiti");
		usu3.setLogin("Matheus@ufscar");
		usu3.setSenha("1235678");
		usu3.setCPF("98765432133");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		usuDAO.cadastrar(usu1);
		usuDAO.cadastrar(usu2);	
		usuDAO.cadastrar(usu3);
		System.out.println("Cadastrado com sucesso!!!");			
	}
	
	public static void testExcluir() {
		Usuario usu = new Usuario();
		usu.setCPF("12345678911");
		
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("Excluido com sucesso com sucesso");
	}
	public static void testBuscarPorCPF() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorCPF("54368495422");
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
