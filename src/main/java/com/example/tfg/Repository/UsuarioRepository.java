package com.example.tfg.Repository;

import com.example.tfg.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Users, Long> {
    Users findByUsername(String username);
}
