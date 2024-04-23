package com.example.tfg.Service;

import com.example.tfg.Model.Users;
import com.example.tfg.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegisterService {

    private  UserRepository userRepository;
    private MongoTemplate mongoTemplate;
    public Users createUser(String username,  String password,String email){
        Users user= new Users(username, password, email);
        userRepository.insert(user);
        //Solo de ejemplo, no lo necesito para el registro de usuario
       // mongoTemplate.update(Users.class)
        // .matching(Criteria.where("imdbId").is(imdbId))
        // .apply(new Update().push(reviewIds).value(review))
        //.first();

      return user;
    }
}
