package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Articulo;

public class ArticleData {
	
	public static ArrayList<Articulo> getArticles() {
		ArrayList<Articulo> list = new ArrayList<Articulo>();

		try {
			Connection cn = DBConnection.getConnection();
			String query = "call getArticles";
			CallableStatement stmt = cn.prepareCall(query);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Articulo article = new Articulo();
				article.setId(rs.getInt(1));
				article.setMark(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setPrice(rs.getDouble(4));
				list.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static void createArticle(Articulo article) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "call saveArticle(?,?,?)";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setString(1, article.getMark());
			stmt.setString(2, article.getDescription());
			stmt.setDouble(3, article.getPrice());
			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateArticleById(Articulo article) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "call updateArticle(?,?,?,?)";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setInt(1, article.getId());
			stmt.setString(2, article.getMark());
			stmt.setString(3, article.getDescription());
			stmt.setDouble(4, article.getPrice());
			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteArticleById(int id) {
		try {
			Connection cn = DBConnection.getConnection();
			String query = "call deleteArticle(?)";
			CallableStatement stmt = cn.prepareCall(query);
			stmt.setInt(1, id);
			stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
