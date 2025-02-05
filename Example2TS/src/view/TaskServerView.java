package view;

import controller.TaskServerController;
import data.TaskData;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TaskServerView extends Application {
	private TaskServerController controller;
	private TextArea logArea;
	private Button startButton;
	private Button stopButton;

	@Override
	public void start(Stage primaryStage) {
		// Inicializa el modelo
		TaskData model = new TaskData();

		// Inicializa el controlador y pasa tanto el modelo como la vista
		controller = new TaskServerController(model, this);

		logArea = new TextArea();
		logArea.setEditable(false);
		logArea.setStyle("-fx-font-size: 14px; -fx-background-color: #f4f4f4; -fx-text-fill: #333;");

		startButton = new Button("Iniciar Servidor");
		stopButton = new Button("Detener Servidor");
		stopButton.setDisable(true);

		// Estilos de botones
		startButton.setStyle(
				"-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");
		stopButton.setStyle(
				"-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20;");

		// Layout para los botones
		HBox buttonBox = new HBox(20, startButton, stopButton);
		buttonBox.setAlignment(Pos.CENTER);

		// Asocia las acciones de los botones
		startButton.setOnAction(e -> {
			controller.startServer();
			showMessage("Iniciando servidor...");
			startButton.setDisable(true);
			stopButton.setDisable(false);
		});

		stopButton.setOnAction(e -> {
			controller.stopServer();
			stopButton.setDisable(true);
			startButton.setDisable(false);
		});

		// Título de la ventana
		Text title = new Text("Servidor de Gestión de Tareas");
		title.setFont(Font.font("Arial", 24));
		title.setFill(Color.DARKSLATEBLUE);

		// Layout principal
		VBox layout = new VBox(20, title, buttonBox, logArea);
		layout.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20;");
		layout.setAlignment(Pos.CENTER);

		// Crear escena y configurar la ventana
		Scene scene = new Scene(layout, 500, 400);
		primaryStage.setTitle("Servidor de Gestión de Tareas");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> controller.stopServer());
		primaryStage.show();
	}

	public void showMessage(String message) {
		Platform.runLater(() -> logArea.appendText(message + "\n"));
	}

	public void setServerRunning(boolean isRunning) {
		startButton.setDisable(isRunning);
		stopButton.setDisable(!isRunning);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
