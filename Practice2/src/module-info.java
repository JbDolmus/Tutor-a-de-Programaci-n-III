module Practice2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	
	opens controller;
	exports controller;
	exports domain;
}
