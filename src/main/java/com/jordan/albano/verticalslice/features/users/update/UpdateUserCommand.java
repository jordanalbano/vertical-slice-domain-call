package com.jordan.albano.verticalslice.features.users.update;


import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record UpdateUserCommand(UUID id, String name, String lastname) implements IRequest<Void> {
}
