package com.example.tfg.Service;

import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<Users> singleUser(String id) {
        return userRepository.findById(id);
    }
}
