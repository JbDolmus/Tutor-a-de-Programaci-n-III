module Tienda {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens business to javafx.graphics, javafx.fxml;
}
