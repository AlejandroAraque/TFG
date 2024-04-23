package com.example.tfg;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite solicitudes desde todos los dominios
                .allowedOrigins("http://localhost:3000") // Permitir solicitudes solo desde localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir los métodos HTTP especificados
                .allowCredentials(true); // Permitir el uso de credenciales (por ejemplo, cookies)
    }
}

