package controller;

import business.TaskClientModel;
import view.TaskClientView;

public class TaskClientController {
    private TaskClientModel model;
    private TaskClientView view;

    public TaskClientController(TaskClientModel model, TaskClientView view) {
        this.model = model;
        this.view = view;

        view.getAddButton().setOnAction(e -> handleAddTask());
    }

    private void handleAddTask() {
        String task = view.getTaskInputText();
        if (!task.isEmpty()) {
            model.sendTask(task);
            view.updateTaskDisplay("Tarea enviada: " + task);
            view.clearTaskInput();
        }
    }

}
