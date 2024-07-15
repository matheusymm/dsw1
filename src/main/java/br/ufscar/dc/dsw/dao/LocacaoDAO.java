package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;

public class LocacaoDAO extends GenericDAO{
    public void insert(Locacao locacao) {
        String sql = "INSERT INTO Locacao (cpfCliente, cnpjLocadora) VALUES (?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locacao.getCpfCliente());
            statement.setString(2, locacao.getCnpjLocadora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locacao> getAll() {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cpfCliente = resultSet.getString("cpfCliente");
                String cnpjLocadora = resultSet.getString("cnpjLocadora");

                Locacao locacao = new Locacao(cpfCliente, cnpjLocadora);
                listaLocacoes.add(locacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaLocacoes;
    }

    public void delete(Locacao locacao) {
        String sql = "DELETE FROM Locacao WHERE cpfCliente = ? AND cnpjLocadora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locacao.getCpfCliente());
            statement.setString(2, locacao.getCnpjLocadora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locacao locacao) {
        String sql = "UPDATE Locacao SET cpfCliente = ?, cnpjLocadora = ? WHERE cpfCliente = ? AND cnpjLocadora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locacao.getCpfCliente());
            statement.setString(2, locacao.getCnpjLocadora());
            statement.setString(3, locacao.getCpfCliente());
            statement.setString(4, locacao.getCnpjLocadora());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Locacao getByCpfCnpj(String cpfCliente, String cnpjLocadora) {
        Locacao locacao = null;
        String sql = "SELECT * FROM Locacao WHERE cpfCliente = ? AND cnpjLocadora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpfCliente);
            statement.setString(2, cnpjLocadora);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                locacao = new Locacao(cpfCliente, cnpjLocadora);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locacao;
    }
}
