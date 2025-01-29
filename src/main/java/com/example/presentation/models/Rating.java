package com.example.presentation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer communication;

    private Integer confidence;

    private Integer content;

    private Integer interaction;

    private Integer liveliness;

    private Integer usageProps;

    private Double totalScore;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn
    Presentation presentation;

    public Rating() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Integer getContent() {
        return content;
    }

    public void setContent(Integer content) {
        this.content = content;
    }

    public Integer getInteraction() {
        return interaction;
    }

    public void setInteraction(Integer interaction) {
        this.interaction = interaction;
    }

    public Integer getLiveliness() {
        return liveliness;
    }

    public void setLiveliness(Integer liveliness) {
        this.liveliness = liveliness;
    }

    public Integer getUsageProps() {
        return usageProps;
    }

    public void setUsageProps(Integer usageProps) {
        this.usageProps = usageProps;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", communication=" + communication +
                ", confidence=" + confidence +
                ", content=" + content +
                ", interaction=" + interaction +
                ", liveliness=" + liveliness +
                ", usageProps=" + usageProps +
                ", totalScore=" + totalScore +
                ", user=" + user +
                ", presentation=" + presentation +
                '}';
    }
}
