package com.example.presentation.controllers;

import com.example.presentation.enums.Status;
import com.example.presentation.exceptions.PresentationException;
import com.example.presentation.exceptions.UserException;
import com.example.presentation.models.Presentation;
import com.example.presentation.models.Rating;
import com.example.presentation.models.User;
import com.example.presentation.services.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ObjectMapper objectMapper;
    @GetMapping("/get-details")
    public ResponseEntity<?> getDetails() {
        List<User> ls = adminService.getAllUsers();
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateStatus(@RequestParam String email, @RequestParam int index) {
        User user = adminService.updateStatus(email, index);
        if (user != null) {
            return new ResponseEntity<>("Updated status", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/assign-presentation")
    public ResponseEntity<?> assignPresentation(@RequestParam int id, @RequestBody Presentation presentation) {
        Presentation rs = adminService.assignPresentation(id, presentation);
        if (rs == null) {
            return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("presentation assigned to " + presentation.getUser().getName(), HttpStatus.OK);
    }

    @GetMapping("/get-presentation/{id}")
    public ResponseEntity<?> getPresentation(@PathVariable("id") int id) {
        Presentation presentation = adminService.getPresentation(id);
        if (presentation == null) {
            return new ResponseEntity<>("something went wrong ", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(presentation, HttpStatus.OK);
    }

    @GetMapping("/get-student-presentation/{id}")
    public ResponseEntity<?> getPresentationByStudent(@PathVariable("id") int id) {
        List<Presentation> presentations = adminService.getPresentationByStudent(id);

        return new ResponseEntity<>(presentations, HttpStatus.OK);
    }

    @PostMapping("/add-score")
    public ResponseEntity<?> addScore(@RequestBody Map<String, Object> payload) {
        if (payload.containsKey("id") && payload.containsKey("score")) {
            int id = Integer.parseInt(payload.get("id").toString());
            Double score = Double.parseDouble(payload.get("score").toString());
            adminService.addScore(score, id);
            return new ResponseEntity<>("presentation score is updated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.OK);
    }

    @PostMapping("/add-rating")
    public ResponseEntity<?> addRating(@RequestBody Map<String, Object> payload) {
        if (payload.containsKey("sid") && payload.containsKey("pid")) {
            int id = Integer.parseInt(payload.get("sid").toString());
            int pid = Integer.parseInt(payload.get("pid").toString());
            Rating rating = objectMapper.convertValue(payload.get("rating"), Rating.class);
            String message = adminService.addRating(id, pid, rating);
            return new ResponseEntity<>(message, HttpStatus.OK);

        }
        return new ResponseEntity<>("something went wrong", HttpStatus.OK);
    }
//pass presentation id
    @GetMapping("/get-rating/{id}")
    public ResponseEntity<?> getRating(@PathVariable("id") int id) {
        Rating rating=adminService.getPresentationRating(id);

        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

//    pass student id
    @GetMapping("/get-student-ratings/{id}")
    public ResponseEntity<?> getStudentRatings(@PathVariable("id") int id) {
        List<Rating> rating=adminService.getStudentRatings(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

}
