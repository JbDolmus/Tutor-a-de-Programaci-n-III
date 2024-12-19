package application;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;

public class PruebaController {
	
	@FXML
	private TextField txtNombre;

	// Event Listener on TextField[#txtNombre].onKeyTyped
	@FXML
	public void eventKey(KeyEvent event) {
		
//		Object evt = event.getSource();
//
//		if (evt.equals(txtNombre)) {
//
//			if (!Character.isLetter(event.getCharacter().charAt(0)) && !event.getCharacter().equals(" ")) {
//				System.out.println("entra");
//				event.consume();
//			}
//
//		}
	}
	

		// Crea un TextFormatter con una expresión regular que solo permite letras y espacios
        TextFormatter<String> textSoloLetras = new TextFormatter<>(change -> {
            // Solo permite letras y espacios (expresión regular)
        	System.out.println(change.getControlNewText());
            if (change.getText().matches("[a-zA-ZÑñ ]*") && change.getControlNewText().length() <= 10) {
                return change; // Permite el cambio si la condición es verdadera
            }
            return null; // No permite el cambio si no es una letra o espacio
        });
        
     // Crea un TextFormatter con una expresión regular que solo permite numeros
        TextFormatter<String> textSoloNumeros = new TextFormatter<>(change -> {
            // Solo permite letras y espacios (expresión regular)
            if (change.getText().matches("[0-9]*") ) {
                return change; // Permite el cambio si la condición es verdadera
            }
            return null; // No permite el cambio si no es una letra o espacio
        });
	
	
	@FXML
    public void initialize() {
        
        
        // Asocia el TextFormatter al TextField
        txtNombre.setTextFormatter(textSoloLetras);
    }
}
