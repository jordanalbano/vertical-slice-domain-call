package com.jordan.albano.verticalslice.exceptions;

public class QuestionNotFoundException extends EntityNotFoundException {
    public QuestionNotFoundException(String questionTextCannotBeEmpty) {
        super(questionTextCannotBeEmpty);
    }
}
