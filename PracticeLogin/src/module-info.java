module PracticeLogin {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.logging;
	requires javafx.base;
	requires java.sql;
	requires commons.dbcp2;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
    exports controller;
}
