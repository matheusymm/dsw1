package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Locacao {
    private Long id;
    private String cpf_cliente;
    private String cnpj_locadora;
    private Date data_locacao;

    public Long getID() {
        return id;
    }

    public String getCpfCliente() {
        return cpf_cliente;
    }

    public String getCnpjLocadora() {
        return cnpj_locadora;
    }

    public Date getDataLocacao() {
        return data_locacao;
    }

    public void setID(Long id) {
        this.id = id;
    }

    public void setCpfCliente(String cpf_cliente) {
        this.cpf_cliente = cpf_cliente;
    }

    public void setCnpjLocadora(String cnpj_locadora) {
        this.cnpj_locadora = cnpj_locadora;
    }

    public void setDataLocacao(Date data_locacao) {
        this.data_locacao = data_locacao;
    }
}