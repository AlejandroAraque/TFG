package com.example.tfg.Controller;

import com.example.tfg.Model.Users;
import com.example.tfg.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> fetchAllStudents() {
        return new ResponseEntity<List<Users>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> getSingleUser(@PathVariable String id){
        return new ResponseEntity<Optional<Users>>(userService.singleUser(id), HttpStatus.OK);
    }

}
