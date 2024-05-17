package com.example.tfg.Service;

import com.example.tfg.Model.Role;
import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    public Users createUser(String username, String password, String email) {
        Users user = new Users(username, password, email);
        user.setRole(Role.USER); // Asignar un rol predeterminado si es necesario
        userRepository.insert(user);
        return user;
    }
}

