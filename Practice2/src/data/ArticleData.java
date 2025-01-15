package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import domain.Articulo;


public class ArticleData {

	private static String fileName = "src/database/articles.json";
	private static JsonUtils<Articulo> jsonUtils = new JsonUtils<>(fileName);

	public static ArrayList<Articulo> getArticles() {
		ArrayList<Articulo> articleList = new ArrayList<>();
		try {
			Map<String, Articulo> articles = jsonUtils.getElements(Articulo.class);
			articleList.addAll(articles.values());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return articleList;
	}

	public static void saveArticle(Articulo article) {
		try {
			Map<String, Articulo> articles = jsonUtils.getElements(Articulo.class);
			if (articles.containsKey(article.getMarca())) {
				System.err.println("Ya existe un artículo con la marca " + article.getMarca());
				return;
			}
			jsonUtils.save(article, article.getMarca());
			System.out.println("Artículo registrado correctamente.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateArticleById(Articulo updatedArticle, String id) {

		try {
			jsonUtils.updateById(updatedArticle, id);
			System.out.println("Artículo actualizado correctamente.");
		} catch (Exception e) {
			System.err.println("Error al actualizar el artículo: " + e.getMessage());
		}
	}

	public static void deleteArticleById(String id) {
		try {
			jsonUtils.deleteById(id, Articulo.class);
			System.out.println("Artículo eliminado correctamente.");
		} catch (Exception e) {
			System.err.println("Error al eliminar el artículo: " + e.getMessage());
		}
	}

}
