package com.jordan.albano.verticalslice.features.calls.findbyid;

import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record FindCallByIdQuery(UUID id) implements IRequest<FindCallByIdDto> {
}
