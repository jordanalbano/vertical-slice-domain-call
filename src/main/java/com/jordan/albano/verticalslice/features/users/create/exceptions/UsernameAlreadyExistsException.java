package com.jordan.albano.verticalslice.features.users.create.exceptions;

import com.jordan.albano.verticalslice.exceptions.BadRequestException;

public class UsernameAlreadyExistsException extends BadRequestException {
    public UsernameAlreadyExistsException() {
        super("El nombre de usuario ya existe");
    }
}
