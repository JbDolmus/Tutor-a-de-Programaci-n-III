module ExampleCS {
	requires javafx.controls;
	requires javafx.graphics;
	
	opens view to javafx.graphics, javafx.fxml;
}
