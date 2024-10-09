package com.starlight.do_ti.controller;

import com.starlight.do_ti.domain.Task;
import com.starlight.do_ti.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService; // Você precisará de um serviço para gerenciar a lógica

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok().body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> optionalTask = taskService.getTaskById(id); // Obtém o Optional
        return optionalTask.map(task -> new ResponseEntity<>(task, HttpStatus.OK)) // Se a tarefa estiver presente, retorna OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Se não estiver presente, retorna NOT FOUND
    }


    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return updatedTask != null ? new ResponseEntity<>(updatedTask, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (taskService.deleteTask(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
