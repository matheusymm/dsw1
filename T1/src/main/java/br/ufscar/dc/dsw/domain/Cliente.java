package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Cliente {
    private Long id;
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private String sexo;
    private Date dataNascimento;
    private String papel;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(String email, String senha, String nome, String cpf, String telefone, String sexo, Date dataNascimento, String papel) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.papel = papel;
    }

    public Cliente(Long id, String email, String senha, String nome, String cpf, String telefone, String sexo, Date dataNascimento, String papel) {
        this(email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
