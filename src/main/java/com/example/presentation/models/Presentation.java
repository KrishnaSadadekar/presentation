package com.example.presentation.models;

import com.example.presentation.enums.Course;
import com.example.presentation.enums.PresentationStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Presentation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private Course course;

    private String topic;
    @Enumerated(EnumType.STRING)
    private PresentationStatus presentationStatus;

    private  Double userTotalScore;

    public Presentation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public PresentationStatus getPresentationStatus() {
        return presentationStatus;
    }

    public void setPresentationStatus(PresentationStatus presentationStatus) {
        this.presentationStatus = presentationStatus;
    }

    public Double getUserTotalScore() {
        return userTotalScore;
    }

    public void setUserTotalScore(Double userTotalScore) {
        this.userTotalScore = userTotalScore;
    }
}
