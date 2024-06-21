package br.com.locacao.persistencia.entidade;

public class Usuario {
	private int id;
	private String nome;
	private String login;
	private String senha;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", login=" + login
		+", senha=" + senha	+ "]";	
	}
	
}
