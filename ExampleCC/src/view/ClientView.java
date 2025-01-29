package view;

import controller.ClientController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ClientView extends Application {
    private TextArea chatArea;
    private TextField inputField;
    private Button sendButton;
    private ClientController controller;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chatbot - Cliente");

        chatArea = new TextArea();
        chatArea.setEditable(false);
        inputField = new TextField();
        inputField.setPromptText("Escribe un mensaje...");
        sendButton = new Button("Enviar");

        HBox inputBox = new HBox(10, inputField, sendButton);
        inputBox.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(chatArea);
        root.setBottom(inputBox);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        controller = new ClientController(this);
        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnAction(e -> sendMessage());
        
        primaryStage.setOnCloseRequest(event -> {
            controller.closeClientConnection();
            Platform.exit();
        });
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            controller.sendMessage(message);
            inputField.clear();
        }
    }

    public void updateChat(String message) {
        chatArea.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
