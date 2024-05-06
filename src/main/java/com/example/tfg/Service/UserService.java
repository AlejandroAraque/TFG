package com.example.tfg.Service;

import org.springframework.stereotype.Service;


@Service
public class UserService {
/*
    private final UserRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;
   @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Método para verificar si un usuario ya existe por nombre de usuario
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Método para verificar si un usuario ya existe por dirección de correo electrónico
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public void register(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<Users> singleUser(String id) {
        return userRepository.findById(id);
    }*/
}
