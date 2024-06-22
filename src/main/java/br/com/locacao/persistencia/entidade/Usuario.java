package br.com.locacao.persistencia.entidade;

import java.sql.Date;

public class Usuario {
	private String nome;
	private String login;
	private String senha;
	private String CPF;
	private String telefone;
	private String sexo;
	private Date datNas;

	public Usuario() {
		/*this.nome = nome;
		this.CPF = CPF;
		this.login = login;
		this.senha = senha;*/
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
	
	@Override
	public String toString() {
	    return "Usuario [nome=" + nome + ", login=" + login + ", senha=" + senha + ", CPF=" + CPF
	            + ", telefone=" + telefone + ", sexo=" + sexo + ", datNas=" + datNas + "]";
	}
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDatNas() {
		return datNas;
	}

	public void setDatNas(Date datNas) {
		this.datNas = datNas;
	}
	
}
