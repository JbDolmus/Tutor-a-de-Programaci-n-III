module Practice2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires com.fasterxml.jackson.annotation;
	
	opens application to javafx.graphics, javafx.fxml;
	
	opens controller;
	exports controller;
	exports domain;
}
