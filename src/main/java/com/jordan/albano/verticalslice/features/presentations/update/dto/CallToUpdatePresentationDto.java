package com.jordan.albano.verticalslice.features.presentations.update.dto;

import java.util.UUID;

public record CallToUpdatePresentationDto(
        String name,
        UUID id
) {
}
