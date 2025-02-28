package com.jordan.albano.verticalslice.features.presentations.delete;

import com.jordan.albano.verticalslice.domain.PresentationEntity;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.UUID;

/**
 * usecase for {@link PresentationEntity}
 */
public record DeletePresentationCommand(
        UUID id
) implements IRequest<Void> {
}
