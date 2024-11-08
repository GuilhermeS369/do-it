package com.starlight.do_ti.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private List<String> tasksIds = new ArrayList<>();

    // Construtor padrão
    public User() {
    }

    public User(String name, String username, String password, String email, List<String> tasksIds) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tasksIds = tasksIds;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTasksIds() {
        return tasksIds;
    }

    public void setTasksIds(List<String> tasks) {
        this.tasksIds = tasksIds;
    }
}