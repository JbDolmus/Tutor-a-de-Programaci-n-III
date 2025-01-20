package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final static String database = "pos_una";
	private final static String user = "root";
	private final static String password = "root";
	private final static String host = "localhost";
	private final static int port = 3306;
	private final static String url = 
			"jdbc:mysql://" + host + ":" + port + "/" + database;

	private static Connection con;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No se encontro la clase " + e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conexion establecida correctamente");
		} catch (SQLException e) {
			System.out.println("Error conexion " + e.getMessage());
		}
		return con;
	}

}