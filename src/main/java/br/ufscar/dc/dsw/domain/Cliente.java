package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Cliente {
    private Long id;    
    private String email;
    private String senha;
    private String cpf;
    private String nome;
    private String telefone;
    private String sexo;
    private Date data_nascimento;
    private String tipo;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(String email, String senha, String cpf, String nome, String telefone, String sexo, Date data_nascimento, String tipo) {
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
        this.tipo = tipo;
    }

    public Cliente(Long id, String email, String senha, String cpf, String nome, String telefone, String sexo, Date data_nascimento, String tipo) {
        this(email, senha, cpf, nome, telefone, sexo, data_nascimento, tipo);
        this.id = id;
    }

    public Long getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    
    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo.toString();
    }

    public Date getDataNascimento() {
        return data_nascimento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
