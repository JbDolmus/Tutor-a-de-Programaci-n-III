package application;

import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		VBox containerLeft = new VBox();
		
		//Agregamos nodos hijos al nodo izquierdo
		Label labelTitle = new Label("Iniciar Sesión");
		labelTitle.setFont(new Font(40));
		
		VBox containerLogin = new VBox();

		Label labelUser = new Label("Usuario");
		labelUser.setFont(new Font(20));

		Label labelPassword = new Label("Contraseña");
		labelPassword.setFont(new Font(20));

		TextField txtUser = new TextField();
		txtUser.setFont(new Font(20));
		txtUser.setPromptText("Ingrese su usuario");
		txtUser.setPrefWidth(341);
		txtUser.setPrefHeight(44);

		PasswordField txtPass = new PasswordField();
		txtPass.setFont(new Font(20));
		txtPass.setPromptText("Digite su contraseña");
		txtPass.setPrefWidth(341);
		txtPass.setPrefHeight(44);

		Button btnLogin = new Button("Iniciar Sesión");
		btnLogin.setFont(new Font(20));
		btnLogin.setPrefWidth(370);
		btnLogin.setPrefHeight(44);
		btnLogin.setMaxWidth(Double.MAX_VALUE);
		btnLogin.setCursor(Cursor.HAND);
		
		containerLogin.getChildren().addAll(labelUser, txtUser, labelPassword, txtPass, btnLogin);
		containerLogin.setAlignment(Pos.TOP_LEFT);
		
		VBox.setMargin(labelUser, new Insets(10, 0, 0, 0));
		VBox.setMargin(labelPassword, new Insets(10, 0, 0, 0));
		VBox.setMargin(btnLogin, new Insets(20, 0, 0, 0));
		
		containerLeft.getChildren().addAll(labelTitle, containerLogin);
		containerLeft.setPrefWidth(422);
		containerLeft.setAlignment(Pos.CENTER);
		VBox.setMargin(containerLogin, new Insets(0, 30, 0, 30));
		
		
		VBox containerRigth = new VBox();
		
		//Agregar nodos en el contenedor derecho
		ImageView imageLogo;
		
		InputStream inputStream;
		inputStream = getClass().getResourceAsStream("/images/logo.png");
		Image image = new Image(inputStream);
		
		imageLogo = new ImageView(image);
		containerRigth.getChildren().add(imageLogo);
		
		containerRigth.setPrefWidth(422);
		containerRigth.setAlignment(Pos.CENTER);
		containerRigth.setBackground(new Background(new BackgroundFill(Color.web("#30373e"), CornerRadii.EMPTY, Insets.EMPTY)));
		

		HBox root = new HBox();

		root.getChildren().addAll(containerLeft, containerRigth);
		HBox.setHgrow(containerLeft, Priority.ALWAYS);
		HBox.setHgrow(containerRigth, Priority.ALWAYS);

		Scene scene = new Scene(root, 854, 503);

		primaryStage.setTitle("HBox Layout");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
