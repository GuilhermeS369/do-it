package com.starlight.do_ti.repository;

import com.starlight.do_ti.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}
