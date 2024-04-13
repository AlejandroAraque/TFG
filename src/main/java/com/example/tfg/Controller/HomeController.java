package com.example.tfg.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Este es el nombre de la vista HTML que quieres mostrar (por ejemplo, "index.html")
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro"; // Nombre de la vista HTML para la página de registro
    }
}

