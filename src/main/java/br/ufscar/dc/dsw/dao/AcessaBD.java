package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessaBD extends GenericDAO{

	public static void main(String[] args) {
		try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/bicicleta";
            Connection con = (Connection) DriverManager.getConnection(url, "mymm", "psql802097");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Cliente");
			System.out.println("Tabela Cliente:");
			while (rs.next()) {
				System.out.print(rs.getString("id") + " ");
				System.out.print(rs.getString("email") + " " + rs.getString("senha") + " ");
				System.out.print(rs.getString("nome") + " " + rs.getString("cpf") + " ");
				System.out.print(rs.getString("telefone") + " " + rs.getString("sexo") + " " + rs.getString("dataNascimento") + "\n");
			}
			rs = stmt.executeQuery("select * from Locadora");
			System.out.println("Tabela Locadora:");
			while (rs.next()) {
				System.out.print(rs.getString("id") + " ");
				System.out.print(rs.getString("email") + " " + rs.getString("senha") + " ");
				System.out.print(rs.getString("cnpj") + " " + rs.getString("nome") + " ");
				System.out.print(rs.getString("cidade") + "\n");
			}
			rs = stmt.executeQuery("select * from Locacao");
			System.out.println("Tabela Locacao:");
			while (rs.next()) {
				System.out.print(rs.getString("id") + " ");
				System.out.print(rs.getString("cpfCliente") + " " + rs.getString("cnpjLocadora") + " ");
				System.out.print(rs.getString("dataLocacao") + "\n");
			}
			stmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("A classe do driver de conexão não foi encontrada!");
		} catch (SQLException e) {
			System.out.println("O comando SQL não pode ser executado!");
		}
	}
}