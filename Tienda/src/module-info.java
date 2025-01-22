module Tienda {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires java.sql;

	opens business to javafx.graphics, javafx.fxml;
	exports business;
	exports domain;
}
