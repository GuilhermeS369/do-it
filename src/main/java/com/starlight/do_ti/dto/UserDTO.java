package com.starlight.do_ti.dto;

import com.starlight.do_ti.domain.Task;
import com.starlight.do_ti.domain.User;

import java.util.List;
import java.util.Objects;

public class UserDTO {

    private String id;
    private String name;
    private String username;
    private List<String> tasksIds;
    private String email;

    public UserDTO(){
    }
    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.username = obj.getUsername();
        this.tasksIds = obj.getTasksIds();
        this.email = obj.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTasksIds() {
        return tasksIds;
    }

    public void setTasksIds(List<String> tasksIds) {
        this.tasksIds = tasksIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
