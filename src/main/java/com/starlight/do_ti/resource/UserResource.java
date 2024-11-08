package com.starlight.do_ti.resource;

import com.starlight.do_ti.domain.Task;
import com.starlight.do_ti.domain.User;
import com.starlight.do_ti.dto.TaskDTO;
import com.starlight.do_ti.dto.UserDTO;
import com.starlight.do_ti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService; // Você precisará de um serviço para gerenciar a lógica

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        Optional<UserDTO> optionalUser = userService.getUserById(id); // Obtém o Optional
        return optionalUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK)) // Se a tarefa estiver presente, retorna OK
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Se não estiver presente, retorna NOT FOUND
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(uri).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasksById(@PathVariable String id) {
        List<TaskDTO> tasks = userService.getUserTasks(id);
        return tasks.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(tasks, HttpStatus.OK) ;
    }
}
