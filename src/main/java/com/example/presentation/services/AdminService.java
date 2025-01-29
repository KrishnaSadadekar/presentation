package com.example.presentation.services;

import com.example.presentation.enums.PresentationStatus;
import com.example.presentation.enums.Status;
import com.example.presentation.exceptions.PresentationException;
import com.example.presentation.exceptions.UserException;
import com.example.presentation.models.Presentation;
import com.example.presentation.models.Rating;
import com.example.presentation.models.User;
import com.example.presentation.repositories.PresentationRepository;
import com.example.presentation.repositories.RatingRepository;
import com.example.presentation.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PresentationRepository presentationRepository;

    @Autowired
    RatingRepository ratingRepository;

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public User updateStatus(String email, int index) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found ! "));
        if (index == 1) {
            user.setStatus(Status.ACTIVE);
        } else if (index == -1) {
            user.setStatus(Status.INACTIVE);
        }
        userRepository.save(user);

        return userRepository.save(user);
    }

    public Presentation assignPresentation(int id, Presentation presentation) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not found! "));
        presentation.setUser(user);
        presentation.setPresentationStatus(PresentationStatus.ASSIGNED);
        return presentationRepository.save(presentation);
    }

    public Presentation getPresentation(int id) {
        return presentationRepository.findById(id).orElseThrow(() -> new PresentationException("invalid presentation id"));
    }

    public List<Presentation> getPresentationByStudent(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not found!"));
        return presentationRepository.findByUser(user);
    }

    public String addScore(Double score, int id) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> new PresentationException("presentation id is not valid"));
        if (!presentation.getPresentationStatus().equals(PresentationStatus.COMPLETED)) {
            throw new PresentationException("Presentation is not completed ");
        }
        presentation.setUserTotalScore(score);

        Presentation rs = presentationRepository.save(presentation);
        return "updated score " + rs.getUserTotalScore();
    }

    //    first find rating on the basis of presentation and user id if it present then update it
    public String addRating(int sid, int pid, Rating rating) {

        User user = userRepository.findById(sid).orElseThrow(() -> new UserException("user not found ! "));
        System.out.println("pid--->" + pid);
        Presentation presentation = presentationRepository.findById(pid).orElseThrow(() -> new PresentationException("presentation id is not valid"));
        Rating save;
        if(!presentation.getPresentationStatus().equals(PresentationStatus.COMPLETED))
        {
            throw new PresentationException("presentation is not completed yet");
        }
        Optional<Rating> byPresentation = ratingRepository.findByPresentation(presentation);

        if (byPresentation.isPresent()) {
            System.out.println("---^^----"+byPresentation.get().getId());

            byPresentation.get().setCommunication(rating.getCommunication());
            byPresentation.get().setConfidence(rating.getConfidence());
            byPresentation.get().setContent(rating.getContent());
            byPresentation.get().setInteraction(rating.getInteraction());
            byPresentation.get().setLiveliness(rating.getLiveliness());
            byPresentation.get().setUsageProps(rating.getUsageProps());
            Double totalScore = toGetTotalScore(rating);
            byPresentation.get().setTotalScore(totalScore);
            save = ratingRepository.save(byPresentation.get());
        } else {
            rating.setPresentation(presentation);
            Double totalScore = toGetTotalScore(rating);
            rating.setTotalScore(totalScore);
            rating.setUser(user);
            save = ratingRepository.save(rating);
        }

        user.setUserTotalScore(save.getTotalScore()+user.getUserTotalScore());
        userRepository.save(user);
        return "rating is updated !" + save.getTotalScore();
    }


    private Double toGetTotalScore(Rating rating) {
        int sum = rating.getCommunication() + rating.getConfidence()
                + rating.getContent() + rating.getInteraction() + rating.getLiveliness() + rating.getUsageProps();
        return sum / 6.0;
    }

    public Rating getPresentationRating(int id) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> new PresentationException("presentation id not valid"));

        return ratingRepository.findByPresentation(presentation).get();
    }

    public List<Rating> getStudentRatings(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException("user not found !"));
        return ratingRepository.findByUser(user);
    }
}
