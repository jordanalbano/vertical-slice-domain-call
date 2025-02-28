package com.jordan.albano.verticalslice.features.calls.delete;


import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

public record DeleteCallCommand(UUID id) implements IRequest<Void> {
}
