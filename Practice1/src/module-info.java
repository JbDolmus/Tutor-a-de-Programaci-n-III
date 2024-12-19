module Practice1 {
	requires javafx.controls;
	requires java.desktop;
	requires java.base;
	requires javafx.fxml;
	requires javafx.base;

	opens business to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
	exports controller;
	exports domain;
}
