package br.ufscar.dc.dsw.domain;

public class Cliente {
    private String email;
    private String CPF;
    private String nome;
    private String telefone;
    private String sexo;
    private String dataNascimento;

    public Cliente(String email, String CPF, String nome, String telefone, String sexo, String dataNascimento) {
        this.email = email;
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
