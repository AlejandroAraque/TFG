package com.example.tfg.Controller;

import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/current")
    public ResponseEntity<Optional<Users>> getCurrentUser() {
        // Obtener la información de autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Verificar si el usuario está autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            // Obtener el nombre de usuario del usuario autenticado
            String username = authentication.getName();

            // Buscar al usuario en la base de datos por el nombre de usuario
            Optional<Users> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            // El usuario no está autenticado, devolver un error 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
