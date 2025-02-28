package com.jordan.albano.verticalslice.features.users.findall;


import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record ExistsUserByIdQuery(
        UUID id
) implements IRequest<Boolean> {
}
