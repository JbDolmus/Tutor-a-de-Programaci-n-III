package business;

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
	private int idArticle = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] listMarks = { "Samsung", "Honor", "Huawei", "Apple", "LG", "Xiaomi" };
		ObservableList<String> items = FXCollections.observableArrayList(listMarks);
		comboMark.setItems(items);
		comboMark.setValue("Seleccione una marca");

		loadArticles();

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
				int index = tableArticles.getSelectionModel().getSelectedIndex();

				if (index > -1) {
					selectedArticle = tableArticles.getItems().get(index);
					idArticle = selectedArticle.getId();

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
					idArticle = deleteArticle.getId();
					Optional<ButtonType> result = showAlert("Confirmación",
							"¿Deseas eliminar el artículo " + deleteArticle.getMark() + "?");

					if (result.get() == ButtonType.OK) {
						ArticleData.deleteArticleById(idArticle);
						showAlert("Exito", "¡Artículo eliminado exitosamente!");
						loadArticles();
						idArticle = 0;
					}
				} else {
					showAlert("Error", "¡Debes seleccionar un artículo para poder editarlo!");
				}

			}

		});

		menuOptions.getItems().addAll(edit, delete);
		tableArticles.setContextMenu(menuOptions);

	}

	private void fillFormArticle(Articulo article) {
		comboMark.getSelectionModel().select(article.getMark());
		txtDescription.setText(article.getDescription());
		txtPrice.setText(article.getPrice() + "");
		btnSave.setText("Actualizar");
		btnCancel.setDisable(false);

	}

	@FXML
	public void btnSaveAction(ActionEvent event) {
		if (validateFormArticle().isEmpty()) {
			Articulo article = setArticle(new Articulo());

			if (selectedArticle == null) {
				ArticleData.createArticle(article);
				showAlert("Éxito", "¡Artículo registrado exitosamente!");
			} else {
				ArticleData.updateArticleById(article);
				;
				showAlert("Éxito", "¡Artículo actualizado exitosamente!");
			}

			loadArticles();
			clearFields();
		} else {
			showAlert("Error", validateFormArticle());
		}
	}

	private void clearFields() {
		comboMark.getSelectionModel().selectFirst();
		txtDescription.setText("");
		txtPrice.setText("");
		btnSave.setText("Registrar");
		btnCancel.setDisable(true);
		selectedArticle = null;
		idArticle = 0;
	}

	private void loadArticles() {
		listArticles = ArticleData.getArticles();
		tableArticles.getItems().clear();
		tableArticles.getColumns().clear();
		tableArticles.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);

		ObservableList<Articulo> articles = FXCollections.observableArrayList(listArticles);

		TableColumn<Articulo, String> markColumn = new TableColumn<Articulo, String>("Marca");
		markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));

		TableColumn<Articulo, String> descriptionColumn = new TableColumn<Articulo, String>("Descripción");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

		TableColumn<Articulo, String> priceColumn = new TableColumn<Articulo, String>("Precio");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

		tableArticles.setItems(articles);
		List<TableColumn<Articulo, String>> columns = Arrays.asList(markColumn, descriptionColumn, priceColumn);
		tableArticles.getColumns().addAll(columns);

	}

	private Optional<ButtonType> showAlert(String title, String message) {
		Alert alert = new Alert(title.equalsIgnoreCase("Error") ? Alert.AlertType.ERROR
				: title.equalsIgnoreCase(" Éxito") ? Alert.AlertType.INFORMATION : Alert.AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle(title);
		alert.setContentText(message);
		return alert.showAndWait();
	}

	private Articulo setArticle(Articulo article) {
		if (idArticle > 0) {
			article.setId(idArticle);
		}
		article.setMark(comboMark.getSelectionModel().getSelectedItem());
		article.setDescription(txtDescription.getText());
		article.setPrice(Double.parseDouble(txtPrice.getText()));
		return article;
	}

	private String validateFormArticle() {
		if (comboMark.getSelectionModel().getSelectedIndex() < 0) {
			return "¡Debes seleccionar una marca!";

		} else if (txtDescription.getText().isEmpty()) {
			return "¡El campo <Descripción> es requerido!";
		} else if (txtPrice.getText().isEmpty()) {
			return "¡El campo <Precio> es requerido!";
		} else {
			return "";
		}
	}

	@FXML
	public void btnCancelAction(ActionEvent event) {
		clearFields();
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
