package com.jordan.albano.verticalslice.exceptions;

public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException() {
        super("El usuario no se encontro");
    }
}
