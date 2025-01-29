package com.example.presentation.repositories;

import com.example.presentation.models.Presentation;
import com.example.presentation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresentationRepository extends JpaRepository<Presentation ,Integer> {
    List<Presentation> findByUser(User user);
}
