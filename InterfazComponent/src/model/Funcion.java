package model;

import javafx.scene.control.TextFormatter;

public class Funcion {

	// Crea un TextFormatter con una expresión regular que solo permite letras y
	// espacios
	public TextFormatter<String> textSoloLetras = new TextFormatter<>(change -> {
		// Solo permite letras y espacios (expresión regular)
		if (change.getText().matches("[a-zA-ZÑñ ]*") && change.getControlNewText().length() <= 10) {
			return change; // Permite el cambio si la condición es verdadera
		}
		return null; // No permite el cambio si no es una letra o espacio
	});

	// Crea un TextFormatter con una expresión regular que solo permite numeros
	public TextFormatter<String> textSoloNumeros = new TextFormatter<>(change -> {
		// Solo permite letras y espacios (expresión regular)
		if (change.getText().matches("[0-9]*")) {
			return change; // Permite el cambio si la condición es verdadera
		}
		return null; // No permite el cambio si no es una letra o espacio
	});

	// Crea un TextFormatter con una expresión regular que permite una longitud de 10
	public TextFormatter<String> textLong = new TextFormatter<>(change -> {
		// Solo permite letras y espacios (expresión regular)
		System.out.println(change.getControlNewText());
		if (change.getControlNewText().length() <= 10) {
			return change; // Permite el cambio si la condición es verdadera
		}
		return null; // No permite el cambio si no es una letra o espacio
	});

}
