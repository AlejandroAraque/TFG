package com.example.tfg.Controller;

import com.example.tfg.Model.Users;
import com.example.tfg.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegisterController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Users usuario) {
        // Verificar si el nombre de usuario ya está en uso
        if (usuarioService.findByUsername(usuario.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre de usuario ya está en uso.");
        }

        // Guardar el usuario en la base de datos
        usuarioService.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente.");
    }
}
