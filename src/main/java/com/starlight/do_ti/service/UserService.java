package com.starlight.do_ti.service;

import com.starlight.do_ti.domain.Task;
import com.starlight.do_ti.domain.User;
import com.starlight.do_ti.dto.TaskDTO;
import com.starlight.do_ti.dto.UserDTO;
import com.starlight.do_ti.repository.TaskRepository;
import com.starlight.do_ti.repository.UserRepository;
import com.starlight.do_ti.service.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    public User findByUsername(String username) {
        Optional<User> obj = userRepository.findByUsername(username);
        return obj.orElseThrow(() -> new ResourceNotFoundException(username));
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserDTO> getUserById(String id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.map(UserDTO::new);
    }

    public User createUser (User user){return userRepository.insert(user);}
    public User updateUser(String id, UserDTO userDTO) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(userDTO.getName());
                    existingUser.setEmail(userDTO.getEmail());
                    existingUser.setUsername(userDTO.getUsername());
                    return userRepository.save(existingUser);
                })
                .orElse(null); // Retorna null se o usuário não existir
    }
    public void deleteUser(String id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        throw new ResourceNotFoundException(id);
    }

    public List<TaskDTO> getUserTasks(String id){
        List<Task> tasks = taskRepository.findByUser(id);
        return tasks.stream()
                .map(task -> new TaskDTO(task))  // Converte para DTO
                .collect(Collectors.toList());

    }


}
