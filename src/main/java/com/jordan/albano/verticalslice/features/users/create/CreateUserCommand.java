package com.jordan.albano.verticalslice.features.users.create;


import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record CreateUserCommand(
        UUID id,
        String name,
        String lastname,
        String email,
        String username,
        String password

        ) implements IRequest<Void> {
}
