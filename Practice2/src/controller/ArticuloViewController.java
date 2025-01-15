package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import data.ArticleData;
import data.ClientData;
import domain.Articulo;
import domain.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
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

	private ContextMenu menuOptions;
	private ArrayList<Articulo> listArticles;
	private Articulo selectedArticle;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// Llenado del combobox
		String[] listClientTypes = { "Samsung", "Honor", "Huawei", "LG", "Xiaomi" };
		ObservableList<String> items = FXCollections.observableArrayList(listClientTypes);
		comboMark.setItems(items);
		comboMark.setValue("Seleccione una marca");
		
		loadArticles();

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
				int index = tableArticles.getSelectionModel().getSelectedIndex();
				if (index > -1) {
					selectedArticle = tableArticles.getItems().get(index);

					fillFormArticle(selectedArticle);
				} else {
					showAlert("Error", "¡Debes seleccionar un artículo para poder editarlo!");
				}
			}
		});
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				int index = tableArticles.getSelectionModel().getSelectedIndex();
				if (index > -1) {
					Articulo deleteArticle = tableArticles.getItems().get(index);
					Optional<ButtonType> result = showAlert("Confirmación",
							"¿Deseas eliminar el cliente " + deleteArticle.getMarca() + "?");

					if (result.get() == ButtonType.OK) {
						ArticleData.deleteArticleById(deleteArticle.getMarca());
						showAlert("Éxito", "¡Artículo eliminado exitosamente!");
						loadArticles();
					}
				} else {
					showAlert("Error", "¡Debes seleccionar un artículo para poder eliminarlo!");
				}
			}
		});

		menuOptions.getItems().addAll(edit, delete);
		tableArticles.setContextMenu(menuOptions);

	}

	@FXML
	public void btnSaveAction(ActionEvent event) {
		
		if (validateFormArticle().isEmpty()) {
			Articulo article = setArticle(new Articulo());

			if (selectedArticle == null) {
				// Guardar nuevo cliente
				ArticleData.saveArticle(article);
				showAlert("Éxito", "¡Artículo registrado exitosamente!");
			} else {
				// Actualizar cliente existente
				ArticleData.updateArticleById(article, article.getMarca());
				showAlert("Éxito", "¡Artículo actualizado exitosamente!");
			}

			loadArticles();
			clearFields();
		} else {
			showAlert("Error", validateFormArticle());
		}
		
	}

	@FXML
	public void btnCancelAction(ActionEvent event) {
		clearFields();
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

	private Articulo setArticle(Articulo article) {
		article.setDescripcion(txtDescription.getText());
		article.setPrecio(Double.parseDouble(txtPrice.getText()));
		article.setMarca(comboMark.getSelectionModel().getSelectedItem());
		return article;
	}

	private String validateFormArticle() {
		if (comboMark.getSelectionModel().getSelectedIndex() < 0) {
			return "¡Debes seleccionar una marca!";
		} else if (txtDescription.getText().isEmpty()) {
			return "¡El campo <Descripción> es requerido!";
		} else if (txtPrice.getText().isEmpty()) {
			return "¡El campo <Precio> es requerido!";
		}
		return "";
	}

	private void clearFields() {
		comboMark.getSelectionModel().selectFirst();
		comboMark.setDisable(false);
		txtDescription.setText("");
		txtPrice.setText("");
		btnSave.setText("Agregar");
		selectedArticle = null;
		btnCancel.setDisable(true);
	}
	
	public void loadArticles() {
		listArticles = ArticleData.getArticles();
		tableArticles.getItems().clear();
		tableArticles.getColumns().clear();
		tableArticles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);

		ObservableList<Articulo> articles = FXCollections.observableArrayList(listArticles);

		TableColumn<Articulo, String> markColum = new TableColumn<>("Marca");
		markColum.setCellValueFactory(new PropertyValueFactory<>("marca"));

		TableColumn<Articulo, String> priceColumn = new TableColumn<>("Precio");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

		TableColumn<Articulo, String> descriptionColumn = new TableColumn<>("Descripción");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

		tableArticles.setItems(articles);
		List<TableColumn<Articulo, String>> columns = Arrays.asList(markColum, priceColumn, descriptionColumn);
		tableArticles.getColumns().addAll(columns);
	}
	
	private void fillFormArticle(Articulo article) {
		txtDescription.setText(article.getDescripcion());
		txtPrice.setText(article.getPrecio()+ "");
		comboMark.getSelectionModel().select(article.getMarca());
		comboMark.setDisable(true);
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
