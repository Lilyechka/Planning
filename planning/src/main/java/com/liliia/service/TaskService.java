package com.liliia.service;

import com.liliia.Repository.TaskRepository;
import com.liliia.model.Task;
import com.liliia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            task.setUserId(taskDetails.getUserId());
            task.setTopic(taskDetails.getTopic());
            task.setDateOfCreation(taskDetails.getDateOfCreation());
            task.setDeadline(taskDetails.getDeadline());
            task.setDescription(taskDetails.getDescription());
            return taskRepository.save(task);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
