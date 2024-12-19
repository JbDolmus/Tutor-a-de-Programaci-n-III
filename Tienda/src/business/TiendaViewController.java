package business;

import java.net.URL;
import java.util.ResourceBundle;

import domain.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;

import javafx.scene.control.TableView;


public class TiendaViewController implements Initializable{
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtIdentification;
	@FXML
	private TextField txtCode;
	@FXML
	private TextArea txtAddress;
	@FXML
	private ComboBox<String> cmbTypeClient;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<Cliente> tableClients;

	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void btnSaveOnAction(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancelOnAction(ActionEvent event) {
		// TODO Autogenerated
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		String[] clientTypes = {"Inactivo", "Activo", "Frecuente"};
		
		ObservableList<String> items = FXCollections.observableArrayList(clientTypes);
		
		cmbTypeClient.setItems(items);
		cmbTypeClient.setValue("Seleccione un tipo de cliente");
		
	}
	
}