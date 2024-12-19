module EjemploCombobox {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.swing;
	requires java.sql;
	requires commons.dbcp2;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller;
	exports controller;
}
