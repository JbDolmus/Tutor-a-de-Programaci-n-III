module TaskProjectServer {
	requires javafx.controls;
	requires java.sql;
	
	opens view to javafx.graphics, javafx.fxml;
}
