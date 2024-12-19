package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.Library;
import domain.Book;
import domain.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.BookConverter;

public class LibraryViewController implements Initializable {
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private ComboBox comboBooks;
	@FXML
	private Button btnAddUser;
	@FXML
	private TableView<User> tableLibraryUsers;

	@FXML
	private TableColumn columnName;
	@FXML
	private TableColumn columnEmail;
	@FXML
	private TableColumn columnBook;

	private ObservableList<User> userList;

	@FXML
	public void buttonAddUser(Event event) {
		addUser();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		userList = javafx.collections.FXCollections.observableArrayList();

		comboBooks.getItems().addAll(createCollectionBooks().listItems());
		comboBooks.setConverter(new BookConverter());
		comboBooks.getSelectionModel().select(0);

//		tableLibraryUsers.setItems(userList);
		
//		this.columnName.setCellValueFactory(new PropertyValueFactory("name"));
//	    columnEmail.setCellValueFactory(new PropertyValueFactory("email"));
//	    columnBook.setCellValueFactory(new PropertyValueFactory("bookName"));
		loadLibraryUser();
	}
	
	private void loadLibraryUser() {
		tableLibraryUsers.getItems().clear();
		tableLibraryUsers.getColumns().clear();
		tableLibraryUsers.setMinWidth(300);
		tableLibraryUsers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_NEXT_COLUMN);
		
		TableColumn nombre = new TableColumn<>("Nombre");
		nombre.setPrefWidth(250);
		nombre.setMinWidth(100);
		
		TableColumn email = new TableColumn<>("Email");
		email.setPrefWidth(250);
		email.setMinWidth(100);
		
		TableColumn librosPrestados = new TableColumn<>("Libros");
		librosPrestados.setPrefWidth(400);
		librosPrestados.setMinWidth(200);
		
		TableColumn nueva = new TableColumn<>("nueva");
		nueva.setPrefWidth(400);
		nueva.setMinWidth(200);
		
		nombre.setCellValueFactory(new PropertyValueFactory("name"));
	    email.setCellValueFactory(new PropertyValueFactory("email"));
	    librosPrestados.setCellValueFactory(new PropertyValueFactory("bookName"));
		
		tableLibraryUsers.getColumns().addAll(nombre, email, librosPrestados);
	}

	private Library<Book> createCollectionBooks() {

		Library<Book> bookLibrary = new Library<>();

		bookLibrary.addItem(new Book("Dracula", "Edgar Allan Poe", 1949));
		bookLibrary.addItem(new Book("Don Quijote", "Miguel de Cervantes", 1967));
		bookLibrary.addItem(new Book("Romeo y Julieta", "William Shackespeare", 1975));

		return bookLibrary;

	}

	// Método privado para validar los campos
	private boolean validateFields() {
		if (txtName.getText() == null || txtName.getText().trim().isEmpty()) {
			showAlert("Error", "El campo 'Nombre' no puede estar vacío.");
			return false;
		}
		if (txtEmail.getText() == null || txtEmail.getText().trim().isEmpty()) {
			showAlert("Error", "El campo 'Email' no puede estar vacío.");
			return false;
		}
		return true;
	}

	// Método para mostrar alertas
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// Método para agregar un usuario
	private void addUser() {
		if (validateFields()) {
			String name = txtName.getText().trim();
			String email = txtEmail.getText().trim();
			// Obtener el libro seleccionado del ComboBox
			Book selectedBook = (Book) comboBooks.getSelectionModel().getSelectedItem();
			String bookName = selectedBook != null ? selectedBook.getTitle() + ", " + selectedBook.getAuthor() + ", " + selectedBook.getPublicationYear() : ""; // Obtén el nombre del libro

			// Crear un nuevo usuario con el nombre del libro seleccionado
			User newUser = new User(name, email, bookName);
			userList.add(newUser);
			
			tableLibraryUsers.setItems(userList);
			
			// Limpiar los campos después de agregar
			clearFields();
		}
	}

	// Método para limpiar los campos
	private void clearFields() {
		txtName.clear();
		txtEmail.clear();
		comboBooks.getSelectionModel().select(0);
	}

}
