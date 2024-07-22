package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO {
    public GenericDAO() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/Bicicleta";
        return DriverManager.getConnection(url, "postgres", "cachorro");
    }
}
