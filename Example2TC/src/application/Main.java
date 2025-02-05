package application;
	
import business.TaskClientModel;
import controller.TaskClientController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.TaskClientView;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
        TaskClientModel model = new TaskClientModel("localhost", 1234);
        TaskClientView view = new TaskClientView();
        new TaskClientController(model, view);

        primaryStage.setTitle("Gestión de Tareas");
        primaryStage.setScene(new Scene(view.getLayout(), 700, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
