package view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TaskClientView {
	
	private VBox layout;
	private HBox taskInputBox;
	private TextField taskInput;
	private Button addButton;
	private TextArea taskDisplay;
	
	public TaskClientView() {
		
		Text title = new Text("Gestión de Tareas");
		title.setFont(Font.font("Arial", 24));
		title.setFill(Color.DARKSLATEBLUE);
		
		
		taskInput = new TextField();
		taskInput.setPromptText("Escribe tu tarea aquí...");
		taskInput.setPrefWidth(300);
		
		addButton = new Button("Agregar Tarea");
		addButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
		
		taskDisplay = new TextArea();
		taskDisplay.setEditable(false);
		taskDisplay.setWrapText(true);
		taskDisplay.setStyle("-fx-font-size: 14px;");
		
		
		taskInputBox = new HBox(10, taskInput, addButton);
		taskInputBox.setAlignment(Pos.CENTER);
		
		layout = new VBox(20, title, taskInputBox, taskDisplay);
		layout.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 20;");
		layout.setAlignment(Pos.CENTER);
		
		addButton.setOnAction(e -> handleAddTask());
	}
	
	public VBox getLayout() {
		return layout;
	}

	public TextField getTaskInput() {
		return taskInput;
	}

	public TextArea getTaskDisplay() {
		return taskDisplay;
	}

	public String getTaskInputText() {
		return taskInput.getText();
	}

	public void updateTaskDisplay(String task) {
		taskDisplay.appendText(task + "\n");
	}

	public void clearTaskInput() {
		taskInput.clear();
	}

	public Button getAddButton() {
		return addButton;
	}
	
	private void handleAddTask() {
		String task = getTaskInputText();
		if(!task.isEmpty()) {
			updateTaskDisplay(task);
			clearTaskInput();
		}
	}

}
