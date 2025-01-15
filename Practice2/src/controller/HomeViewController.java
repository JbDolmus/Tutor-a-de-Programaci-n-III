package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeViewController {
	@FXML
	private VBox homePanel;
	@FXML
	private Button btnViewClients;
	@FXML
	private Button btnViewArticles;

	// Event Listener on Button[#btnViewClients].onAction
	@FXML
	public void btnViewClientsAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TiendaView.fxml"));
			Parent root = loader.load();
			TiendaViewController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.btnViewClients.getScene().getWindow();
			temp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#btnViewArticles].onAction
	@FXML
	public void btnViewArticlesAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ArticuloView.fxml"));
			Parent root = loader.load();
			ArticuloViewController controller = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.btnViewArticles.getScene().getWindow();
			temp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
