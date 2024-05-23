package com.example.tfg.Repository;

import com.example.tfg.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

;
@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Users> findByEmail(String email);

    Optional<String> findUsernameById(String providerId);
}
