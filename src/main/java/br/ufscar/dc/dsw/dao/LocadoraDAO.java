package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;

public class LocadoraDAO extends GenericDAO{
    public void insert(Locadora locadora) {
        String sql = "INSERT INTO Locadora (email, senha, cnpj, nome, cidade) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getSenha());
            statement.setString(3, locadora.getCnpj());
            statement.setString(4, locadora.getNome());
            statement.setString(5, locadora.getCidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locadora> getAll() {
        List<Locadora> listaLocadoras = new ArrayList<>();
        String sql = "SELECT * FROM Locadora";           

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String papel = resultSet.getString("papel");

                Locadora locadora = new Locadora(id, email, senha, cnpj, nome, cidade, papel);
                listaLocadoras.add(locadora);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaLocadoras;
    }
    
    public Locadora getByCidade(String cidade) {
    	List<Locadora> listaLocadoras = new ArrayList<>();
        Locadora locadora = null;
        String sql = "SELECT * FROM Locadora WHERE cidade = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
	        	Long id = resultSet.getLong("id");
	            String email = resultSet.getString("email");
	            String senha = resultSet.getString("senha");
	            String cnpj = resultSet.getString("cnpj");
	            String nome = resultSet.getString("nome");
	            String papel = resultSet.getString("papel");
	
	            locadora = new Locadora(id, email, senha, cnpj, nome, cidade, papel);
	            listaLocadoras.add(locadora);
	        }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locadora;
    }

    public Locadora getByEmail(String email) {
    	List<Locadora> listaLocadoras = new ArrayList<>();
        Locadora locadora = null;
        String sql = "SELECT * FROM Locadora WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
	        	Long id = resultSet.getLong("id");
	            String cidade = resultSet.getString("cidade");
	            String senha = resultSet.getString("senha");
	            String cnpj = resultSet.getString("cnpj");
	            String nome = resultSet.getString("nome");
	            String papel = resultSet.getString("papel");
	
	            locadora = new Locadora(id, email, senha, cnpj, nome, cidade, papel);
	            listaLocadoras.add(locadora);
	        }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locadora;
    }

    public void delete(Locadora locadora) {
        String sql = "DELETE FROM Locadora WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, locadora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locadora locadora) {
        String sql = "UPDATE Locadora SET email = ?, senha = ?, cnpj = ?, nome = ?, cidade = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getSenha());
            statement.setString(3, locadora.getCnpj());
            statement.setString(4, locadora.getNome());
            statement.setString(5, locadora.getCidade());
            statement.setLong(6, locadora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Locadora getById(Long id) {
        Locadora locadora = null;
        String sql = "SELECT * FROM Locadora WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String papel = resultSet.getString("papel");

                locadora = new Locadora(id, email, senha, cnpj, nome, cidade, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locadora;
    }
}
