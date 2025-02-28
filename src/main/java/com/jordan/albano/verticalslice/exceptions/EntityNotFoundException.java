package com.jordan.albano.verticalslice.exceptions;

public class EntityNotFoundException extends BadRequestException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
