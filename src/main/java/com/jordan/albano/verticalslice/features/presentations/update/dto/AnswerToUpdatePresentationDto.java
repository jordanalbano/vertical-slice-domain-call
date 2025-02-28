package com.jordan.albano.verticalslice.features.presentations.update.dto;

import java.util.UUID;

public record AnswerToUpdatePresentationDto(
        UUID id,
        String questionId,
        String content
) {
}
