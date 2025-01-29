package com.example.presentation.controllers;

import com.example.presentation.dto.UserDto;
import com.example.presentation.enums.Status;
import com.example.presentation.exceptions.UserException;
import com.example.presentation.models.User;
import com.example.presentation.repositories.UserRepository;
import com.example.presentation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/adduser")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent())
        {
            return new ResponseEntity<>("email is already registered ",HttpStatus.BAD_REQUEST);
        }
        User user = userService.addUser(userDto);
        return new ResponseEntity<>("Welcome  " + user.getName(), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean login = userService.login(email, password);
        if (login) {
            return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable("id")int id) {
        User user =userService.getDetails(id);
        if(user.getStatus().equals(Status.INACTIVE))
        {
            return new ResponseEntity<>("Account is deactivated! ",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        String message = userService.updateUser(userDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    

}
