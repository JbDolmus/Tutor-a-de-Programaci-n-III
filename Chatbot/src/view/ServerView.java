package view;

import controller.ServerController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ServerView extends Application{
	private TextArea chatArea;
	private ServerController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ChatBot - Servidor");
		
		chatArea = new TextArea();
		chatArea.setEditable(false);
		
		VBox root = new VBox(chatArea);
		Scene scene = new Scene(root, 400, 300);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
		controller = new ServerController(this);
		
		primaryStage.setOnCloseRequest((WindowEvent event) -> {
			controller.stopServer();
			Platform.exit();
		});
		
	}
	

	public void updateChat(String message) {
		chatArea.appendText(message + "\n");
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
