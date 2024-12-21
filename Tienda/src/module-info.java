module Tienda {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;

	opens business to javafx.graphics, javafx.fxml;
}
