package view;


import controller.ClientController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClientView extends Application {

	private TextArea chatArea;
	private TextField inputFiel;
	private Button sendButton;
	private ClientController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Chatbot - Cliente");
		
		
		chatArea = new TextArea();
		chatArea.setEditable(false);
		inputFiel = new TextField();
		inputFiel.setPromptText("Escribe un mensaje...");
		sendButton = new Button("Enviar");
		
		HBox inputBox = new HBox(10, inputFiel, sendButton);
		inputBox.setPadding(new Insets(10));
		
		BorderPane root = new BorderPane();
		root.setCenter(chatArea);
		root.setBottom(inputBox);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		controller = new ClientController(this);
		sendButton.setOnAction(e -> sendMessage());
		
		primaryStage.setOnCloseRequest(event -> {
			controller.closeClientConnection();
			Platform.exit();
		});
		
	}
	
	private void sendMessage() {
		String message = inputFiel.getText().trim();
		if(!message.isEmpty()) {
			controller.sendMessage(message);
			inputFiel.clear();
		}
	}

	public void updateChat(String menssage) {
		chatArea.appendText(menssage + "\n");
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
