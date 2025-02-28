package com.jordan.albano.verticalslice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    private UUID id;

    private String content;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @ManyToOne(optional = false)
    private PresentationEntity presentation;

    public Answer() {
        this.date = LocalDateTime.now();
    }

    public Answer(UUID id) {
        this.id = id;
        this.date = LocalDateTime.now();
    }

    public Answer(String content) {
        this.content = content;
        this.date = LocalDateTime.now();
    }

    public Answer(String content, UUID questionID) {
        this.content = content;
        this.question = new Question(questionID);
        this.date = LocalDateTime.now();
    }

}