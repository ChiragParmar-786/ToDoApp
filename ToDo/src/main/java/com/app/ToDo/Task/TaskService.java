package com.app.ToDo.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    void createTask(String title);
}
