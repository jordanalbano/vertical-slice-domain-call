package com.jordan.albano.verticalslice.exceptions;

public class AnswerNotFoundException extends EntityNotFoundException {
    public AnswerNotFoundException(String answerContentCannotBeEmpty) {
        super(answerContentCannotBeEmpty);
    }
}
