package com.example.tfg.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
/*
    private final UserService userService;

    // Constructor para la inyección de dependencias
    public RegisterController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        // Verificar si el usuario ya existe en la base de datos
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso");
        }
        // Verificar si el correo electrónico ya existe en la base de datos
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("El correo electrónico ya está en uso");
        }
        // Guardar el nuevo usuario en la base de datos
        try {
            userService.register(user);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario: " + e.getMessage());
        }
    }*/
}
