package com.jordan.albano.verticalslice.features.users.delete;


import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record DeleteUserCommand(UUID id) implements IRequest<Void> {
}
