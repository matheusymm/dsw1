package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

import br.ufscar.dc.dsw.util.Data;

public class Locacao {
    private Long id;
    private String cpfCliente;
    private String cnpjLocadora;
    private LocalDateTime dataLocacao;

    public Locacao(Long id) {
        this.id = id;
    }

    public Locacao(Long id, String cpfCliente, String cnpjLocadora, LocalDateTime dataLocacao) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
        this.dataLocacao = Data.setHoraCheia(dataLocacao);
    }

    public Locacao(String cpfCliente, String cnpjLocadora, LocalDateTime dataLocacao) {
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
        this.dataLocacao = Data.setHoraCheia(dataLocacao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCnpjLocadora() {
        return cnpjLocadora;
    }

    public void setCnpjLocadora(String cnpjLocadora) {
        this.cnpjLocadora = cnpjLocadora;
    }

    public LocalDateTime getDataLocacao() {
        return dataLocacao;
    }

    public String getDataLocacaoString() {
        return Data.localDateTimeToString(dataLocacao);
    }

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }
}
