package business;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.awt.Menu;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import data.ClientData;
import domain.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
	private ComboBox<String> cmbTypeClient;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private TableView<Cliente> tableClients;
	private ContextMenu menuOptions;

	private ArrayList<Cliente> listClients;
	private Cliente selectedClient;

	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void btnSaveOnAction(ActionEvent event) {

		if (validateFormClient().isEmpty()) {
			Cliente client = setClient(new Cliente());

			if (selectedClient == null) {
				ClientData.createClient(client);
				showAlert("Éxito", "¡Cliente registrado exitosamente!");
			} else {
				ClientData.updateClientById(client, client.getIdentification());
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		String[] clientTypes = { "Inactivo", "Activo", "Frecuente" };

		ObservableList<String> items = FXCollections.observableArrayList(clientTypes);

		cmbTypeClient.setItems(items);
		cmbTypeClient.setValue("Seleccione un tipo de cliente");

		loadClients();

		menuOptions = new ContextMenu();
		MenuItem edit = new MenuItem("Editar");
		MenuItem delete = new MenuItem("Eliminar");

		// Agregar icono del item Editar
		Image editIcon = new Image(getClass().getResourceAsStream("/img/edit.png"));
		ImageView editIconView = new ImageView(editIcon);
		editIconView.setFitWidth(16);
		editIconView.setFitHeight(16);

		// Agregar icono del item Eliminar
		Image deleteIcon = new Image(getClass().getResourceAsStream("/img/delete.png"));
		ImageView deleteIconView = new ImageView(deleteIcon);
		deleteIconView.setFitWidth(16);
		deleteIconView.setFitHeight(16);

		edit.setGraphic(editIconView);
		delete.setGraphic(deleteIconView);

		edit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int index = tableClients.getSelectionModel().getSelectedIndex();

				if (index > -1) {
					selectedClient = tableClients.getItems().get(index);

					fillFormClient(selectedClient);
				} else {
					showAlert("Error", "¡Debes seleccionar un cliente para poder editarlo!");
				}
			}
		});

		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int index = tableClients.getSelectionModel().getSelectedIndex();

				if (index > -1) {
					Cliente deleteClient = tableClients.getItems().get(index);
					Optional<ButtonType> result = showAlert("Confirmación",
							"¿Deseas eliminar el cliente " + deleteClient.getName() + "?");

					if (result.get() == ButtonType.OK) {
						ClientData.deleteClientById(deleteClient.getIdentification());
						showAlert("Exito", "¡Cliente eliminado exitosamente!");
						loadClients();
					}
				} else {
					showAlert("Error", "¡Debes seleccionar un cliente para poder editarlo!");
				}

			}

		});

		menuOptions.getItems().addAll(edit, delete);
		tableClients.setContextMenu(menuOptions);

	}

	private Optional<ButtonType> showAlert(String title, String message) {
		Alert alert = new Alert(title.equalsIgnoreCase("Error") ? Alert.AlertType.ERROR
				: title.equalsIgnoreCase(" Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle(title);
		alert.setContentText(message);
		return alert.showAndWait();
	}

	private String validateFormClient() {

		if (txtName.getText().isEmpty()) {
			return "¡El campo <Nombre> es requerido!";
		} else if (txtIdentification.getText().isEmpty()) {
			return "¡El campo <Identificación> es requerido!";
		} else if (txtCode.getText().isEmpty()) {
			return "¡El campo <Codigo> es requerido!";
		} else if (txtAddress.getText().isEmpty()) {
			return "¡El campo <Direccion> es requerido!";
		} else if (cmbTypeClient.getSelectionModel().getSelectedIndex() < 0) {
			return "¡Debes seleccionar un tipo de cliente!";
		} else {
			return "";
		}
	}

	private Cliente setClient(Cliente client) {
		client.setName(txtName.getText());
		client.setIdentification(txtIdentification.getText());
		client.setCode(txtCode.getText());
		client.setAddress(txtAddress.getText());
		client.setTypeClient(cmbTypeClient.getSelectionModel().getSelectedItem());
		return client;
	}

	private void clearFields() {
		txtName.setText("");
		txtIdentification.setText("");
		txtIdentification.setDisable(false);
		txtCode.setText("");
		txtAddress.setText("");
		cmbTypeClient.getSelectionModel().selectFirst();
		btnSave.setText("Registrar");
		btnCancel.setDisable(true);
		selectedClient = null;
	}

	public void loadClients() {
		listClients = ClientData.getClients();
		tableClients.getItems().clear();
		tableClients.getColumns().clear();
		tableClients.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);

		ObservableList<Cliente> clients = FXCollections.observableArrayList(listClients);

		TableColumn<Cliente, String> nameColumn = new TableColumn<Cliente, String>("Nombre");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Cliente, String> identificationColumn = new TableColumn<Cliente, String>("Identificación");
		identificationColumn.setCellValueFactory(new PropertyValueFactory<>("identification"));

		TableColumn<Cliente, String> codeColumn = new TableColumn<Cliente, String>("Código");
		codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

		TableColumn<Cliente, String> addressColumn = new TableColumn<Cliente, String>("Dirección");
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Cliente, String> typeClientColumn = new TableColumn<Cliente, String>("Tipo de cliente");
		typeClientColumn.setCellValueFactory(new PropertyValueFactory<>("typeClient"));

		tableClients.setItems(clients);
		List<TableColumn<Cliente, String>> columns = Arrays.asList(nameColumn, identificationColumn, codeColumn,
				addressColumn, typeClientColumn);
		tableClients.getColumns().addAll(columns);

	}

	private void fillFormClient(Cliente client) {
		txtName.setText(client.getName());
		txtIdentification.setText(client.getIdentification());
		txtIdentification.setDisable(true);
		txtCode.setText(client.getCode());
		txtAddress.setText(client.getAddress());
		cmbTypeClient.getSelectionModel().select(client.getTypeClient());
		btnSave.setText("Actualizar");
		btnCancel.setDisable(false);
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
