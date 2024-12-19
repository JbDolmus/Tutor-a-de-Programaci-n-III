package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import java.util.*;
import domain.Cliente;

public class TiendaViewController implements Initializable {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtIdentification;
	@FXML
	private TextField txtCode;
	@FXML
	private TextArea txtAddress;
	@FXML
	private ComboBox<String> cmbClientType;
	@FXML
	private Button btnRegister;
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<Cliente> tbListClients;
	private ContextMenu menuOptions;
	private ArrayList<Cliente> listClients;
	private Cliente selectedClient;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] listClientTypes = { "Inactivo", "Activo", "Frecuente" };
		ObservableList<String> items = FXCollections.observableArrayList(listClientTypes);
		cmbClientType.setItems(items);
		cmbClientType.setValue("Seleccione el tipo de cliente");

		listClients = new ArrayList<>();

		loadClients();

		menuOptions = new ContextMenu();
		MenuItem edit = new MenuItem("Editar");
		MenuItem delete = new MenuItem("Eliminar");
		
		// Agregar ícono al item "Editar"
		Image editIcon = new Image(getClass().getResourceAsStream("/img/edit.png")); // Ruta al archivo
		ImageView editIconView = new ImageView(editIcon);
		editIconView.setFitWidth(16); // Ajusta el tamaño del ícono si es necesario
		editIconView.setFitHeight(16);
		edit.setGraphic(editIconView);

		// Agregar ícono al item "Eliminar"
		Image deleteIcon = new Image(getClass().getResourceAsStream("/img/delete.png")); // Ruta al archivo
		ImageView deleteIconView = new ImageView(deleteIcon);
		deleteIconView.setFitWidth(16);
		deleteIconView.setFitHeight(16);
		delete.setGraphic(deleteIconView);

		edit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int index = tbListClients.getSelectionModel().getSelectedIndex();
				if (index > -1) {
					selectedClient = tbListClients.getItems().get(index);

					fillFormClient(selectedClient);
				} else {
					showAlert("Error", "¡Debes seleccionar un cliente para poder editarlo!");
				}
			}
		});
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int index = tbListClients.getSelectionModel().getSelectedIndex();
				if (index > -1) {
					Cliente deleteClient = tbListClients.getItems().get(index);
					Optional<ButtonType> result = showAlert("Confirmación",
							"¿Deseas eliminar el cliente " + deleteClient.getNombre() + "?");

					if (result.get() == ButtonType.OK) {
						listClients.remove(deleteClient);
						loadClients();
					}
				} else {
					showAlert("Error", "¡Debes seleccionar un cliente para poder eliminarlo!");
				}
			}
		});

		menuOptions.getItems().addAll(edit, delete);
		tbListClients.setContextMenu(menuOptions);
	}

	// Event Listener on Button[#btnRegister].onAction
	@FXML
	public void btnRegisterOnAction(ActionEvent event) {

		if (validateFormClient().isEmpty()) {

			if (selectedClient == null) {
				// Instanciamos el objeto cliente y lo llenamos
				Cliente client = new Cliente();
				listClients.add(setClient(client));
				showAlert("Éxito", "¡Cliente registrado exitosamente!");
			} else {

				for (Cliente client : listClients) {
					if (client.equals(selectedClient)) {
						setClient(client);
						break;
					}
				}
				showAlert("Éxito", "¡Cliente actualizado exitosamente!");
			}

			loadClients();
			clearFields();
		} else {
			showAlert("Error", validateFormClient());
		}
	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancelOnAction(ActionEvent event) {
		clearFields();
	}

	private String validateFormClient() {
		if (txtName.getText().isEmpty()) {
			return "¡El campo <Nombre> es requerido!";
		} else if (txtIdentification.getText().isEmpty()) {
			return "¡El campo <Cédula> es requerido!";
		} else if (txtCode.getText().isEmpty()) {
			return "¡El campo <Código> es requerido!";
		} else if (txtAddress.getText().isEmpty()) {
			return "¡El campo <Dirección> es requerido!";
		} else if (cmbClientType.getSelectionModel().getSelectedIndex() < 0) {
			return "¡Debes seleccionar el tipo de Cliente!";
		}
		return "";
	}

	// Método para mostrar alertas
	private Optional<ButtonType> showAlert(String title, String message) {
		Alert alert = new Alert(title.equalsIgnoreCase("Error") ? Alert.AlertType.ERROR
				: title.equalsIgnoreCase("Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle(title);
		alert.setContentText(message);
		return alert.showAndWait();
	}

	private Cliente setClient(Cliente client) {
		client.setNombre(txtName.getText());
		client.setCedula(txtIdentification.getText());
		client.setCodigoCliente(txtCode.getText());
		client.setDireccionCliente(txtAddress.getText());
		client.setTipoCliente(cmbClientType.getSelectionModel().getSelectedItem());
		return client;
	}

	private void clearFields() {
		txtName.setText("");
		txtIdentification.setText("");
		txtCode.setText("");
		txtAddress.setText("");
		cmbClientType.getSelectionModel().selectFirst();
		btnRegister.setText("Registrar");
		selectedClient = null;
		btnCancel.setDisable(true);
	}

	public void loadClients() {
		tbListClients.getItems().clear();
		tbListClients.getColumns().clear();
		tbListClients.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);

		ObservableList<Cliente> clients = FXCollections.observableArrayList(listClients);

		TableColumn<Cliente, String> nameColum = new TableColumn<>("Nombre");
		nameColum.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		TableColumn<Cliente, String> identificationColumn = new TableColumn<>("Cédula");
		identificationColumn.setCellValueFactory(new PropertyValueFactory<>("cedula"));

		TableColumn<Cliente, String> codeColumn = new TableColumn<>("Código");
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("codigoCliente"));

		TableColumn<Cliente, String> addressColumn = new TableColumn<>("Dirección");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("direccionCliente"));

		TableColumn<Cliente, String> typeClientColumn = new TableColumn<>("Tipo de Cliente");
		typeClientColumn.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));

		tbListClients.setItems(clients);
		List<TableColumn<Cliente, String>> columns = Arrays.asList(nameColum, identificationColumn, codeColumn,
				addressColumn, typeClientColumn);
		tbListClients.getColumns().addAll(columns);
	}

	private void fillFormClient(Cliente cliente) {
		txtName.setText(cliente.getNombre());
		txtIdentification.setText(cliente.getCedula());
		txtCode.setText(cliente.getCodigoCliente());
		txtAddress.setText(cliente.getDireccionCliente());
		cmbClientType.getSelectionModel().select(cliente.getTipoCliente());
		btnRegister.setText("Actualizar");
		btnCancel.setDisable(false);
	}
}
