package com.starlight.do_ti.service;

import com.starlight.do_ti.domain.Task;
import com.starlight.do_ti.domain.User;
import com.starlight.do_ti.dto.TaskDTO;
import com.starlight.do_ti.repository.TaskRepository;
import com.starlight.do_ti.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository; // Repositório para interagir com o MongoDB
    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieve all tasks from the database.
     *
     * @return a list of tasks
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieve a task by its ID.
     *
     * @param id the ID of the task
     * @return an Optional containing the task if found, or empty if not found
     */
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    /**
     * Create a new task in the database.
     *
     * @param task the task to be created
     * @return the created task
     */
    public TaskDTO createTask(Task task) {
        Optional<User> userOptional = userRepository.findById(task.getUserId());
        User user = userOptional.get();

        // Define o usuário na tarefa
        task.setUser(user);
        task.setDueDate(LocalDateTime.now());
        Task savedTask = taskRepository.save(task); // Salva a tarefa

        // Adiciona o ID da tarefa ao usuário e salva o usuário
        user.getTasksIds().add(savedTask.getId());
        userRepository.save(user);
        TaskDTO taskDTO = new TaskDTO();
        //Transforma tarefa em tarefadto
        BeanUtils.copyProperties(task, taskDTO);
        return taskDTO;

    }

    /**
     * Update an existing task by its ID.
     *
     * @param id the ID of the task to update
     * @param task the new task data
     * @return the updated task if found and updated, or null if not found
     */
    public Task updateTask(String id, Task task) {
        if (taskRepository.existsById(id)) {
            task.setId(id); // Assumindo que o ID está na classe Task
            return taskRepository.save(task);
        }
        return null; // Retorna null se a tarefa não for encontrada
    }

    /**
     * Delete a task by its ID.
     *
     * @param id the ID of the task to delete
     * @return true if the task was deleted, false if not found
     */
    public boolean deleteTask(String id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true; // Retorna true se a tarefa foi deletada
        }
        return false; // Retorna false se a tarefa não for encontrada
    }
}
