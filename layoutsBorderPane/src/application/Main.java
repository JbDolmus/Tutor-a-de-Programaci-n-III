package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Prueba.fxml"));
		
//		Button btn1 = new Button("Boton 1");
//		Button btn2 = new Button("Boton 2");
//		Button btn3 = new Button("Boton 3");
//		Button btn4 = new Button("Boton 4");
//		Button btn5 = new Button("Boton 5");
//
//		BorderPane root = new BorderPane();
//		
//		root.setCenter(btn1);
//		btn1.setMaxWidth(Double.MAX_VALUE);
//		btn1.setMaxHeight(Double.MAX_VALUE);
//		BorderPane.setMargin(btn1, new Insets(5, 5, 5, 5));
//		
//		root.setTop(btn2);
//		btn2.setMaxWidth(Double.MAX_VALUE);
//		btn2.setMaxHeight(Double.MAX_VALUE);
//		
//		root.setBottom(btn3);
//		btn3.setMaxWidth(Double.MAX_VALUE);
//		btn3.setMaxHeight(Double.MAX_VALUE);
//		
//		root.setLeft(btn4);
//		btn4.setMaxWidth(Double.MAX_VALUE);
//		btn4.setMaxHeight(Double.MAX_VALUE);
//		BorderPane.setMargin(btn4, new Insets(5, 5, 5, 5));
//		
//		root.setRight(btn5);
//		btn5.setMaxWidth(Double.MAX_VALUE);
//		btn5.setMaxHeight(Double.MAX_VALUE);
//		BorderPane.setMargin(btn5, new Insets(5, 5, 5, 5));
//		
		Scene scene = new Scene(root);
		primaryStage.setTitle("BorderPane Layout");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
