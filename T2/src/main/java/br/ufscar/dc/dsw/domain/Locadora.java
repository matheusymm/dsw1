package br.ufscar.dc.dsw.domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class Locadora {
    @NotBlank
    @Column(nullable=false, length=100)
    private String email;

    @NotBlank
    @Column(nullable=false, length=64)
    private String senha;

    @NotBlank
    @Column(nullable=false, length=18)
    private String cnpj;

    @NotBlank
    @Column(nullable=false, length=60)
    private String nome;

    @NotBlank
    @Column(nullable=false, length=60)
    private String cidade;

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
