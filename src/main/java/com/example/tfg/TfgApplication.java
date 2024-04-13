package com.example.tfg;

import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class TfgApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfgApplication.class, args);
    }
    @Bean
    CommandLineRunner runner(UsuarioRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            String email = "jandro.araquerobles@gmail.com";
            Users username = new Users(
                    "Alejandro",
                    "alejandro",
                    email


            );
            //repository.insert(username);
        };
    }


}
