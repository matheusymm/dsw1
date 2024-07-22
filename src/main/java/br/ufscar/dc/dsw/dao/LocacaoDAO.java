package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;

public class LocacaoDAO extends GenericDAO {
    public Boolean insert(Locacao locacao) {
        String sql = "INSERT INTO Locacao (cpfCliente, cnpjLocadora, dataLocacao) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            if(this.getBydataLocacao(locacao.getDataLocacao(), locacao.getCnpjLocadora()) != null) {
                statement.close();
                conn.close();
                return false;
            }

            String dataLocacaoStr = locacao.getDataLocacao().toString();
            statement.setString(1, locacao.getCpfCliente());
            statement.setString(2, locacao.getCnpjLocadora());
            statement.setString(3, dataLocacaoStr);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public List<Locacao> getAll() {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	long idLocacao = resultSet.getInt("id");
                String cpfCliente = resultSet.getString("cpfCliente");
                String cnpjLocadora = resultSet.getString("cnpjLocadora");
                String dataLocacaoStr = resultSet.getString("dataLocacao");

                LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);

                Locacao locacao = new Locacao(idLocacao, cpfCliente, cnpjLocadora, dataLocacao);
                listaLocacoes.add(locacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaLocacoes;
    }

    public void delete(Locacao locacao) {
        String sql = "DELETE FROM Locacao WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, locacao.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locacao locacao) {
        String sql = "UPDATE Locacao SET cpfCliente = ?, cnpjLocadora = ?, dataLocacao = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            String dataLocacaoStr = locacao.getDataLocacao().toString();
            statement.setString(1, locacao.getCpfCliente());
            statement.setString(2, locacao.getCnpjLocadora());
            statement.setString(3, dataLocacaoStr);
            statement.setFloat(4, locacao.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locacao> getByCpf(String cpfCliente) {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao WHERE cpfCliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpfCliente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cnpjLocadora = resultSet.getString("cnpjLocadora");
                String dataLocacaoStr = resultSet.getString("dataLocacao");

                LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);

                Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
                listaLocacoes.add(locacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaLocacoes;
    }

    public List<Locacao> getByCnpj(String cnpjLocadora) {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao WHERE cnpjLocadora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpjLocadora);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	Long id = (long) resultSet.getInt("id");
                String cpfCliente = resultSet.getString("cpfCliente");
                String dataLocacaoStr = resultSet.getString("dataLocacao");

                LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);

                Locacao locacao = new Locacao(id, cpfCliente, cnpjLocadora, dataLocacao);
                listaLocacoes.add(locacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaLocacoes;
    }

    public Locacao getBydataLocacao(LocalDateTime diaHora, String cnpj) {
        String sql = "SELECT * from locacao where dataLocacao = ? AND cnpjLocadora = ?";
        Locacao locacao = null;
        try {
            Connection con = this.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            String stringDiaHora = diaHora.toString();
            statement.setString(1, stringDiaHora);
            statement.setString(2, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String cpfCliente = resultSet.getString("cpfCliente");
                String cnpjLocadora = resultSet.getString("cnpjLocadora");

                locacao = new Locacao(id, cpfCliente, cnpjLocadora, diaHora);
            }

            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locacao;
    }
    
    
}
