package com.starlight.do_ti.repository;

import com.starlight.do_ti.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    // Você pode adicionar métodos de consulta personalizados aqui, se necessário

    /**
     * Find tasks by their status.
     *
     * @param completed the status of the tasks
     * @return a list of tasks with the specified status
     */
    List<Task> findByCompleted(Boolean completed);


    /**
     * Find tasks by their user ID.
     *
     * @param userId the ID of the user to whom the tasks are assigned
     * @return a list of tasks assigned to the specified user
     */
    List<Task> findByUserId(String userId);
}
