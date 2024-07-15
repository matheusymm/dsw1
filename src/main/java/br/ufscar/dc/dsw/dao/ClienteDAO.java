package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends GenericDAO{
    public void insert(Cliente cliente) {
        String sql = "INSERT INTO Cliente (email, senha, nome, cpf, telefone, sexo, dataNascimento, papel) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getNome());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setDate(7, cliente.getDataNascimento());
            statement.setString(8, cliente.getPapel());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";           

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNascimento = resultSet.getDate("dataNascimento");
                String papel = resultSet.getString("papel");

                Cliente cliente = new Cliente(id, email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
        return listaClientes;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET email = ?, senha = ?, nome = ?, cpf = ?, telefone = ?, sexo = ?, dataNascimento = ?, papel = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getSenha());
            statement.setString(3, cliente.getNome());
            statement.setString(4, cliente.getCpf());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setDate(7, cliente.getDataNascimento());
            statement.setString(8, cliente.getPapel());
            statement.setLong(9, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente getById(Long id) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNascimento = resultSet.getDate("dataNascimento");
                String papel = resultSet.getString("papel");

                cliente = new Cliente(id, email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Cliente getByEmail(String email) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNascimento = resultSet.getDate("dataNascimento");
                String papel = resultSet.getString("papel");

                cliente = new Cliente(id, email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }
}
