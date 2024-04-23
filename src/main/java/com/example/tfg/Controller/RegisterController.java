package com.example.tfg.Controller;

import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class RegisterController {
    private final UserRepository userRepository;

    // Constructor para la inyección de dependencias
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        // Verificar si el usuario ya existe en la base de datos
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso");
        }
        // Verificar si el correo electrónico ya existe en la base de datos
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("El correo electrónico ya está en uso");
        }
        // Guardar el nuevo usuario en la base de datos
        userRepository.save(user);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}
