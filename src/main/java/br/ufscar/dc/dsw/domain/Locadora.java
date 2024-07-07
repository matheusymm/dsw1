package br.ufscar.dc.dsw.domain;

public class Locadora {
    private Long id;
    private String email;
    private String senha;
    private String cnpj;
    private String nome;
    private String cidade;

    public Locadora(Long id) {
        this.id = id;
    }

    public Locadora(String email, String senha, String cnpj, String nome, String cidade) {
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Locadora(Long id, String email, String senha, String cnpj, String nome, String cidade) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cidade = cidade;
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
}
