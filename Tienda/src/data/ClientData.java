package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import domain.Cliente;

public class ClientData {

	public static ArrayList<Cliente> getClients() {
		ArrayList<Cliente> list = new ArrayList<Cliente>();

		try {
			Connection cn = DBConnection.getConnection();
			String query = "call getClients";
			CallableStatement stmt = cn.prepareCall(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt(1));
				cliente.setName(rs.getString(2));
				cliente.setIdentification(rs.getString(3));
				cliente.setCode(rs.getString(4));
				cliente.setAddress(rs.getString(5));
				cliente.setTypeClient(rs.getString(6));
				list.add(cliente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void createClient(Cliente client) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "call saveClient(?,?,?,?,?)";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getIdentification());
			stmt.setString(3, client.getCode());
			stmt.setString(4, client.getAddress());
			stmt.setString(5, client.getTypeClient());
			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateClientById(Cliente client) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "call updateClient(?,?,?,?,?,?)";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setInt(1, client.getId());
			stmt.setString(2, client.getName());
			stmt.setString(3, client.getIdentification());
			stmt.setString(4, client.getCode());
			stmt.setString(5, client.getAddress());
			stmt.setString(6, client.getTypeClient());
			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteClientById(int id) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "call deleteClient(?)";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setInt(1, id);
			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
