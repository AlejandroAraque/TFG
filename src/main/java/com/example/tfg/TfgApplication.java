package com.example.tfg;

import com.example.tfg.Security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication

@Import(SecurityConfig.class)
public class TfgApplication {

    public static void main(String[] args) {
        SpringApplication.run(TfgApplication.class, args);
    }
/*
   @Bean
    CommandLineRunner runner(UserRepository repository, MongoTemplate mongoTemplate) {
        return args -> {

            String email = "jamah@gmail.com";
            Users users = new Users(
                    "Jamila",
                    "ahmed",
                    email
            );


            repository.findByEmail(email)
                    .ifPresentOrElse(s -> {
                        System.out.println(s + "already exits");
                    }, () -> {
                        System.out.println("Inserting student" + users);
                        repository.insert(users);
                    });


        };

    }*/
}
