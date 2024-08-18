package br.ufscar.dc.dsw.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import br.ufscar.dc.dsw.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name="Locadora")
public class Locadora extends AbstractEntity<Long> {
    @NotBlank
    @Column(nullable=false, length=100)
    private String email;

    @NotBlank
    @Column(nullable=false, length=64)
    private String senha;

    @NotBlank
    @UniqueCNPJ(message="{Unique.locadora.CNPJ}")
    @Size(min=18, max=18, message="{Size.locadora.CNPJ}")
    @Column(nullable=false, unique=true, length=18)
    private String CNPJ;

    @NotBlank
    @Size(min=3, max=50)
    @Column(nullable=false, length=50)
    private String nome;

    @NotBlank
    @Column(nullable=false, length=100)
    private String cidade;

    @NotBlank
    @Column(nullable=false, length=13)
    private String papel="ROLE_LOCADORA";

    // @OneToMany(mappedBy="locadora")
    // private List<Cliente> clientes;

    @OneToMany(mappedBy="locadora")
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

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cnpj) {
        this.CNPJ = cnpj;
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

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
