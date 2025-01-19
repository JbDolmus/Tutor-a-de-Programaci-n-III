package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TextArea;

import javafx.scene.control.TableView;

public class ArticuloViewController implements Initializable {
	@FXML
	private ComboBox<String> comboMark;
	@FXML
	private TextArea txtDescription;
	@FXML
	private TextField txtPrice;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<Articulo> tableArticles;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] listMarks = {"Samsung", "Honor", "Huawei", "Apple", "LG", "Xiaomi"};
		ObservableList<String> items = FXCollections.observableArrayList(listMarks);
		comboMark.setItems(items);
		comboMark.setValue("Seleccione una marca");
		
		
	}

	@FXML
	public void btnSaveAction(ActionEvent event) {
		
	}

	@FXML
	public void btnCancelAction(ActionEvent event) {
		
	}
	
	
	public void closeWindows() {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeView.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
