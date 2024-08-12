package br.ufscar.dc.dsw.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Bicicleta")
public class Cliente extends AbstractEntity<Long> {
    @NotBlank
    @Column(nullable=false, length=100)
    private String email;

    @NotBlank
    @Column(nullable=false, length=64)
    private String senha;

    @NotBlank
    @Column(nullable=false, length=60)
    private String nome;

    @NotBlank
    @Column(nullable=false, length=14)
    private String cpf;

    @NotBlank
    @Column(nullable=false, length=20)
    private String telefone;

    @NotBlank
    @Column(nullable=false, length=10)
    private String sexo;

    @NotBlank
    @Column(nullable=false, length=10)
    private Date dataNascimento;

    @NotBlank    
    @Column(nullable=false, length=5)
    private String papel;
    
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
