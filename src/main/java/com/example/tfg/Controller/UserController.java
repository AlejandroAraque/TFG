package com.example.tfg.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
/*
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> fetchAllStudents() {
        return new ResponseEntity<List<Users>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Users>> getSingleUser(@PathVariable String id){
        return new ResponseEntity<Optional<Users>>(userService.singleUser(id), HttpStatus.OK);
    }
*/
}
