module InterfazComponent {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;

	
    exports controller;
}
