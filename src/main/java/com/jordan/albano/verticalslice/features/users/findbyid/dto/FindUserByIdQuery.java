package com.jordan.albano.verticalslice.features.users.findbyid.dto;


import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record FindUserByIdQuery(UUID id) implements IRequest<FindUserByIdDto> {
}
