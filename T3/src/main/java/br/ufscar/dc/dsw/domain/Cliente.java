package br.ufscar.dc.dsw.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.ufscar.dc.dsw.validation.UniqueCPF;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value={"locacoes"})
@Entity 
@Table(name="Cliente")
public class Cliente extends AbstractEntity<Long> {
    @NotBlank
    @Column(nullable=false, length=100)
    private String email;

    @NotBlank
    @Column(nullable=false, length=64)
    private String senha;

    @NotBlank
    @UniqueCPF(message="{Unique.cliente.CPF}")
    @Size(min=14, max=14)
    @Column(nullable=false, unique=true, length=14)
    private String cpf;

    @NotBlank
    @Size(min=3, max=50)
    @Column(nullable=false, length=50)
    private String nome;

    @NotBlank
    @Column(nullable=false, length=20)
    private String telefone;

    @NotBlank
    @Column(nullable=false, length=10)
    private String sexo;

    @NotBlank
    @Column(nullable=false, length=10)
    private String dataNascimento;

    @NotBlank
    @Column(nullable=false, length=30)
    private String papel;

    @Column(nullable=false)
    private boolean enabled;

    @OneToMany(mappedBy="cliente")
    private List<Locacao> locacoes;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
