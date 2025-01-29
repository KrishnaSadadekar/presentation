package com.example.presentation.controllers;

import com.example.presentation.models.User;
import com.example.presentation.repositories.PresentationRepository;
import com.example.presentation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presentation")
public class PresentationController {

    @Autowired
    UserService userService;

    @PutMapping("/update-status")
    public ResponseEntity<?> updatePresentationStatus(@RequestParam int id, @RequestParam int index) {
        String message = userService.updatePresentationStatus(id, index);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
