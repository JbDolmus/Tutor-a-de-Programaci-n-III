package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import business.ClientHandler;
import data.TaskData;
import javafx.application.Platform;
import view.TaskServerView;

public class TaskServerController {
	private TaskData model;
	private TaskServerView view;
	private static final int PORT = 1234;
	private static Set<ClientHandler> clients = new HashSet<>();
	private ServerSocket serverSocket;
	private boolean running;

	public TaskServerController(TaskData model, TaskServerView view) {
		this.model = model;
		this.view = view;
	}

	public void startServer() {
		new Thread(() -> {
			try {
				// Crea el ServerSocket una sola vez y lo reutiliza
				if (serverSocket == null || serverSocket.isClosed()) {
					serverSocket = new ServerSocket(PORT);
					Platform.runLater(() -> view.showMessage("Servidor iniciado en el puerto " + PORT));
				}

				while (!running) { // Mientras el servidor no esté detenido
					try {
						Socket socket = serverSocket.accept();
						ClientHandler clientHandler = new ClientHandler(socket, model);
						clients.add(clientHandler);
						new Thread(clientHandler).start();
					} catch (IOException e) {
						// Verifica si la excepción fue por el cierre del socket
						if (serverSocket.isClosed()) {
							break; // Sale del bucle si el socket fue cerrado
						}
						// De lo contrario, maneja otros errores de manera adecuada
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public void stopServer() {
		running = false; // Detenemos el servidor
		try {
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close(); // Cierra el servidor para que el método accept deje de bloquear
			}
			for (ClientHandler client : clients) {
				System.out.println("entro a cerrar");
				client.sendMessage("Servidor detenido");
				client.closeConnection();
			}
			clients.clear();
			view.showMessage("Servidor detenido.");
		} catch (IOException e) {
			view.showMessage("Error al detener el servidor: " + e.getMessage());
		}
	}

	public static void broadcast(String message, ClientHandler sender) {
		for (ClientHandler client : clients) {
			if (client != sender) {
				client.sendMessage(message);
			}
		}
	}

	public static void removeClient(ClientHandler client) {
		clients.remove(client);
	}
}
