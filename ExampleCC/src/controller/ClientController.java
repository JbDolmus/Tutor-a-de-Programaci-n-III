package controller;

import business.ChatClient;
import view.ClientView;

public class ClientController {
    private ClientView view;
    private ChatClient client;

    public ClientController(ClientView view) {
        this.view = view;
        this.client = new ChatClient(this);
    }

    public void sendMessage(String message) {
        view.updateChat("Tú: " + message);
        client.sendMessage(message);
    }

    public void addMessage(String message) {
        view.updateChat(message);
    }
    
    public void closeClientConnection() {
        client.closeConnection();
    }

}
