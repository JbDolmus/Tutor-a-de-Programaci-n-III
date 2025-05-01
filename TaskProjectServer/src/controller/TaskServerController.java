package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import business.ClientHandle;
import data.TaskData;
import javafx.application.Platform;
import view.TaskServerView;

public class TaskServerController {
	
	private TaskData model;
	private TaskServerView view;
	private static final int PORT = 1234;
	private static Set<ClientHandle> clients = new HashSet<ClientHandle>();
	private ServerSocket serverSocket;
	private boolean running;
	
	public TaskServerController(TaskData model, TaskServerView view) {
		this.model = model;
		this.view = view;		
	}
	
	public void starSever() {
		new Thread(() -> {
			try {
				if(serverSocket == null || serverSocket.isClosed()) {
					serverSocket = new ServerSocket(PORT);
					Platform.runLater(()-> view.showMessage("Servidor iniciado en el puerto "+ PORT));
				}
				
				while(!running) {
					try {
						Socket socket = serverSocket.accept();
						ClientHandle clientHandle = new ClientHandle(socket, model);
						clients.add(clientHandle);
						new Thread(clientHandle).start();
					} catch (Exception e) {
						if(serverSocket.isClosed()) {
							break;
						}
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void stopServer() {
		running = false;
		try {
			if(serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
			}
			
			for(ClientHandle client : clients) {
				client.sendMessage("Servidor detenido");
				client.closeConnection();
			}
			clients.clear();
			view.showMessage("Servidor detenido");
			
		} catch (Exception e) {
			view.showMessage("Error al detener el servidor: " + e.getMessage());
		}
	}

	public static void broadcast(String message, ClientHandle sender) {
		for(ClientHandle client : clients) {
			if(client != sender) {
				client.sendMessage(message);
			}
		}
	}

	public static void removeClient(ClientHandle client) {
		clients.remove(client);
	}

}
