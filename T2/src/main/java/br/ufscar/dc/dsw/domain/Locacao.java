package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Locacao extends AbstractEntity<Long> {
    @NotNull
    @OneToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

    @NotNull
    @OneToOne
    @JoinColumn(name="locadora_id")
    private Locadora locadora;

    @NotNull
    @Column(nullable=false)
    private LocalDateTime dataLocacao;

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

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }
}
