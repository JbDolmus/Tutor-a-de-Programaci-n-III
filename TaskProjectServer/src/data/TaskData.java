package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskData {
	private final static String database = "db_task";
	private final static String user = "root";
	private final static String password = "root";
	private final static String host = "localhost";
	private final static int port = 3306; 
	private final static String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
	
	private static Connection con;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error, no se encontro  la clase "+ e.getMessage());
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Error en la conexion "+ e.getMessage());
		}
		return con;
	}
	
	public void addTask(String task) {
		try {
			String query = "call saveTask(?)";
			CallableStatement stmt = getConnection().prepareCall(query);
			stmt.setString(1, task);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
