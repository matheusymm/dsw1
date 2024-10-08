package br.ufscar.dc.dsw.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Locacao")
public class Locacao extends AbstractEntity<Long> {
    @Column(nullable=false, length=20)
    private String data;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @NotNull(message="{NotNull.locacao.locadora}")
    @ManyToOne
    @JoinColumn(name="locadora_id")
    private Locadora locadora;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Locadora getLocadora() {
        return locadora;
    }

    public void setLocadora(Locadora locadora) {
        this.locadora = locadora;
    }
}
