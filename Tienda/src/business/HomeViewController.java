package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class HomeViewController {
	@FXML
	private Button btnViewClients;
	@FXML
	private Button btnViewArticles;


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
