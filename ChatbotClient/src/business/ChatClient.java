package business;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import controller.ClientController;
import javafx.application.Platform;

public class ChatClient {

	private static final String HOST = "localhost";
	private static final int PORT = 1234;
	private ClientController controller;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String serverMessage;

	public ChatClient(ClientController controller) {
		this.controller = controller;
		connectServer();
	}

	private void connectServer() {

		try {
			socket = new Socket(HOST, PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			controller.addMessage("Conectato al servidor");

			new Thread(() -> {
				try {
					while((serverMessage = in.readLine()) != null) {
						Platform.runLater(() -> controller.addMessage(serverMessage));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();

		} catch (Exception e) {
			controller.addMessage("Error al conectar con el servidor!");
		}

	}
	
	public void sendMessage(String message) {
		if(out != null) {
			out.println(message);
		}
	}
	
	
	public void closeConnection() {
		try {
			if(in != null) {
				in.close();
			}
			
			if(out != null) {
				out.close();
			}
			
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
			controller.addMessage("Conexión cerrada!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
