package br.ufscar.dc.dsw.domain;

public class Locacao {
    private Long id;
    private String cpfCliente;
    private String cnpjLocadora;
    //  TODO: LocalDateTime?

    public Locacao(Long id, String cpfCliente, String cnpjLocadora) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.cnpjLocadora = cnpjLocadora;
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
}
