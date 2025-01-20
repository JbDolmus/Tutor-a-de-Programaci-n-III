package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import domain.Client;

public class ClientData {
	
	public static  LinkedList<Client> getAll(){
		LinkedList<Client>  list = new LinkedList<Client>(); 
		try {
			Connection cn = DBConnection.getConnection();
			String query = "{call getAllClient}";
			CallableStatement stmt = cn.prepareCall(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Client c = new Client();
				c.setId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setAddress(rs.getString(4));
				c.setPostal_code(rs.getInt(5));
				c.setLatitude(rs.getDouble(6));
				c.setLongitude(rs.getDouble(7));	
				c.setBirthdate(LocalDate.parse(rs.getString(8)));
				list.add(c);
			}
		}catch(SQLException e) {
			
		}
		
		return list;
	}
	public static void deleteClient(int id) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "{call deleteClient(?)}";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setInt(1, id);
			stmt.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void saveClient(Client client) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "{call saveClient(?,?,?,?,?,?,?)}";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setString(1, client.getName());
			stmt.setString(2, client.getEmail());
			stmt.setString(3, client.getAddress());
			stmt.setInt(4, client.getPostal_code());
			stmt.setDouble(5, client.getLatitude());
			stmt.setDouble(6, client.getLongitude());
			stmt.setDate(7, Date.valueOf(client.getBirthdate()) );
			stmt.executeQuery();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
