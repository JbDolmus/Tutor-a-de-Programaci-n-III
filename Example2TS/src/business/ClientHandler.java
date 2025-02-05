package business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import data.TaskData;
import controller.TaskServerController;

public class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private TaskData model;

    public ClientHandler(Socket socket, TaskData model) {
        this.socket = socket;
        this.model = model;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            String message;
            while ((message = in.readLine()) != null) {
                processCommand(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    private void processCommand(String command) {
        try {
            if (command.startsWith("ADD")) {
                String task = command.substring(4);
                model.addTask(task);
                TaskServerController.broadcast("TASK_ADDED: " + task, this);
            } else if (command.startsWith("DELETE")) {
                int taskId = Integer.parseInt(command.substring(7));
                model.deleteTask(taskId);
                TaskServerController.broadcast("TASK_DELETED: " + taskId, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void closeConnection() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            TaskServerController.removeClient(this);
        }
    }
}
