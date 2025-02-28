package com.jordan.albano.verticalslice.features.presentations.create.dto;

import java.util.UUID;

public record AnswerToCreatePresentationDto(
        UUID id,
        String questionId,
        String content
) {
}
