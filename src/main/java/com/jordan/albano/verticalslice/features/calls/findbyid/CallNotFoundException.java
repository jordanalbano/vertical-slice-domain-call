package com.jordan.albano.verticalslice.features.calls.findbyid;


import com.jordan.albano.verticalslice.exceptions.EntityNotFoundException;

public class CallNotFoundException extends EntityNotFoundException {
    public CallNotFoundException() {
        super("La convoctoria no existe");
    }
}
