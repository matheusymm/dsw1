package br.ufscar.dc.dsw.domain;

public class Locador {
    private String email;
    private String CNPJ;
    private String nome;
    private String cidade;

    public Locador(String email, String CNPJ, String nome, String cidade) {
        this.email = email;
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }
}
