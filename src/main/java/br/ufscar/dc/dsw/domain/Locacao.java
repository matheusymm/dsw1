package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

public class Locacao {
    private Long id;
    private String cpfCliente;
    private String cnpjLocadora;
    private LocalDateTime dataLocacao;

    public Locacao(Long id, String cpfCliente, String cnpjLocadora, LocalDateTime dataLocacao) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
        this.dataLocacao = dataLocacao;
    }

    public Locacao(String cpfCliente, String cnpjLocadora) {
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
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

    public void setDataLocacao(LocalDateTime dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public static LocalDateTime setHoraCheia(LocalDateTime data) {
        if(data.getSecond() > 0 || data.getMinute() > 0) {
            data = data.plusHours(1);
            data = data.withMinute(0);
            data = data.withSecond(0);
        }
        return data;
    }
}
