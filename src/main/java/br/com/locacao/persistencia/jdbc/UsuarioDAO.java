package br.com.locacao.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.locacao.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();
	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome, login, senha) values (?, ?, ?)";
		
		try {
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());//substitui o ? pelo dado do usuario
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			//Executando o comando SQL no banco
			preparador.execute();
			//fechando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=? , senha=? where id=?";
		
		try {
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());//substitui o ? pelo dado do usuario
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			//Executando o comando SQL no banco
			preparador.execute();
			//fechando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=?";
		
		try {
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setInt(1, usu.getId());
			//Executando o comando SQL no banco
			preparador.execute();
			//fechando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void salvar(Usuario usuario) {
		if(usuario.getId()>0) {
			alterar(usuario);
		}
		else {
			cadastrar(usuario);
		}
	}
	/*
	 * Buscar de um registro de banco de dados pelo id do usuario
	 * @param é um inteiro que identifica o usuario a ser buscado
	 * @return retorna o usuario buscado ou nulo caso não seja encontrado
	 * 
	 * */
	
	public Usuario buscarPorId(Integer id) {
		String sql = "Select * from usuario where id=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();//o executeQuery() retorna um resultSet
			if(resultado.next()==true) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
			
				return usuario;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/*Realiza a busca de todos registro da tabela de usuários
	 * 
	 * @return retorna uma lista com os usuarios ou vazia
	 * */
	
	public List<Usuario>  buscarTodos() {
		String sql = "Select * from usuario";
		List<Usuario> lista = new ArrayList<Usuario>();
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			
			ResultSet resultado = preparador.executeQuery();//o executeQuery() retorna um resultSet
			while(resultado.next()==true) {
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
			
				lista.add(usuario);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
}
