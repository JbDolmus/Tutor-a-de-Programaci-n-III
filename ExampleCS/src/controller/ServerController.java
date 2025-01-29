package controller;

import business.ChatServer;
import view.ServerView;

public class ServerController {
    private ServerView view;
    private ChatServer server;

    public ServerController(ServerView view) {
        this.view = view;
        this.server = new ChatServer(this);
        server.startServer();
    }

    public void addMessage(String message) {
        view.updateChat(message);
    }
    
    public void stopServer() {
        server.stopServer();
    }
}
