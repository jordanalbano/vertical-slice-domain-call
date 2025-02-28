package com.jordan.albano.verticalslice.domain;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    private UUID id;

    @Column(name = "text", nullable = false, length = 500)
    private String text;

    @Column(name = "required", nullable = false)
    private Boolean required;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Question() {
        this.questionType = QuestionType.FREE_TEXT;
    }

    public Question(UUID id) {
        this.id = id;
        this.questionType = QuestionType.FREE_TEXT;
    }

    public Question(String text, Boolean required) {
        this.text = text;
        this.required = required;
        this.questionType = QuestionType.FREE_TEXT;
    }

    public Question(String text, Boolean required, QuestionType questionType) {
        this.text = text;
        this.required = required;
        this.questionType = questionType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

}
