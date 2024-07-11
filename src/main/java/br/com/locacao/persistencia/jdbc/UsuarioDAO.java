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
		String sql = "insert into usuario (nome, CPF, login, senha, telefone, sexo, datnas, role) values (?, ?, ?, ?, ?, ?, ?, 'usu')";
		
		try {
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());//substitui o ? pelo dado do usuario
			preparador.setString(2, usu.getCPF());
			preparador.setString(3, usu.getLogin());
			preparador.setString(4, usu.getSenha());
			preparador.setString(5, usu.getTelefone());
			preparador.setString(6, usu.getSexo());
			preparador.setDate(7, usu.getDatNas());
			
			//Executando o comando SQL no banco
			preparador.execute();
			//fechando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=? , senha=?, telefone=?, sexo=?, datnas=? where CPF=?";//altera com base no cpf
		
		try {
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1, usu.getNome());//substitui o ? pelo dado do usuario
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setString(4, usu.getTelefone());
			preparador.setString(5, usu.getSexo());
			preparador.setDate(6, usu.getDatNas());
			preparador.setString(7, usu.getCPF());
			//Executando o comando SQL no banco
			preparador.execute();
			//fechando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void excluir(Usuario usu) {
		String sql = "delete from usuario where CPF=?";
		
		try {
			PreparedStatement preparador  = con.prepareStatement(sql);
			preparador.setString(1, usu.getCPF());
			//Executando o comando SQL no banco
			preparador.execute();
			//fechando o objeto preparador
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void salvar(Usuario usuario) {
		if(usuario.getCPF()!=null) {
			alterar(usuario);
		}
		
			cadastrar(usuario);
		
	}
	/*
	 * Buscar de um registro de banco de dados pelo id do usuario
	 * @param é um inteiro que identifica o usuario a ser buscado
	 * @return retorna o usuario buscado ou nulo caso não seja encontrado
	 * 
	 * */
	
	public Usuario buscarPorCPF(String CPF) {
		String sql = "Select * from usuario where CPF=?";
		
		try (PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, CPF);
			
			ResultSet resultado = preparador.executeQuery();//o executeQuery() retorna um resultSet
			if(resultado.next()==true) {
				Usuario usuario = new Usuario();
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setCPF(resultado.getString("CPF"));
				usuario.setSexo(resultado.getString("sexo"));
				usuario.setTelefone(resultado.getString("telefone"));
				usuario.setDatNas(resultado.getDate("datnas"));
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
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setCPF(resultado.getString("CPF"));
				usuario.setSexo(resultado.getString("sexo"));
				usuario.setTelefone(resultado.getString("telefone"));
				usuario.setDatNas(resultado.getDate("datnas"));
			
				lista.add(usuario);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public Usuario autenticar(Usuario usuConsulta) {
		String sql = "Select * from usuario where login=? and senha=?";
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			usuario.setCPF(resultado.getString("CPF"));
			usuario.setSexo(resultado.getString("sexo"));
			usuario.setTelefone(resultado.getString("telefone"));
			usuario.setRole(resultado.getString("role"));
			//usuario.setDatNas(resultado.getDate("datnas"));
			return usuario;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
