package br.ufscar.dc.dsw.domain;

public class Locadora {
    private Long id;
    private String email;
    private String senha;
    private String cnpj;
    private String nome;
    private String cidade;
    private String papel;

    public Locadora(Long id) {
        this.id = id;
    }

    public Locadora(String email, String senha, String cnpj, String nome, String cidade, String papel) {
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cidade = cidade;
        this.papel = papel;
    }

    public Locadora(Long id, String email, String senha, String cnpj, String nome, String cidade, String papel) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cidade = cidade;
        this.papel = papel;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) { 
        this.cnpj = cnpj; 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) { 
        this.cidade = cidade; 
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) { 
        this.papel = papel; 
    }
}
