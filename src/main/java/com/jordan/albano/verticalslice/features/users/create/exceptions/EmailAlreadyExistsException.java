package com.jordan.albano.verticalslice.features.users.create.exceptions;

import com.jordan.albano.verticalslice.exceptions.BadRequestException;

public class EmailAlreadyExistsException extends BadRequestException {
    public EmailAlreadyExistsException() {
        super("El email ya existe");
    }
}
