package com.example.presentation.repositories;

import com.example.presentation.models.Presentation;
import com.example.presentation.models.Rating;
import com.example.presentation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
    Optional<Rating> findByPresentation(Presentation presentation);

    List<Rating> findByUser(User user);
}
