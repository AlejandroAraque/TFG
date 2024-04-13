package com.example.tfg.Controller;
import com.example.tfg.Model.Users;
import com.example.tfg.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users usuario) {
        Users usuarioEncontrado = usuarioService.findByUsername(usuario.getUsername());
        if (usuarioEncontrado != null && passwordEncoder.matches(usuario.getPassword(), usuarioEncontrado.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = "tuTokenJWT"; // Genera tu token JWT aquí
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {

            return ResponseEntity.status(401).build();
        }
    }
}
