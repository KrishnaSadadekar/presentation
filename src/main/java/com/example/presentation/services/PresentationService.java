package com.example.presentation.services;

import com.example.presentation.exceptions.UserException;
import com.example.presentation.models.Presentation;
import com.example.presentation.models.User;
import com.example.presentation.repositories.PresentationRepository;
import com.example.presentation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PresentationService {
    @Autowired
    PresentationRepository presentationRepository;

    @Autowired
    UserRepository userRepository;

    public List<Presentation> getPresentation() {
        return presentationRepository.findAll();
    }

    public void addPresentation(Integer id,Presentation presentation) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("User not found "));
    }
}
