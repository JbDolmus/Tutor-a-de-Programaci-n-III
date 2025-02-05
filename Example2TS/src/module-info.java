module Example2TS {
	requires javafx.controls;
	requires java.sql;
	requires javafx.graphics;
	
	opens view to javafx.graphics, javafx.fxml;
    exports controller;
    exports business;
}
