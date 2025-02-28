package com.jordan.albano.verticalslice.features.presentations.create;

import com.jordan.albano.verticalslice.domain.PresentationEntity;
import com.jordan.albano.verticalslice.features.presentations.create.dto.AnswerToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.CallToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.ExtensionToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.InvestigationToCreatePresentationDto;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.List;
/**
 * usecase for {@link PresentationEntity}
 */
public record CreatePresentationCommand(
        CallToCreatePresentationDto callToApply,
        String userId,
        List<AnswerToCreatePresentationDto> answers,
        List<ExtensionToCreatePresentationDto> extensions,
        List<InvestigationToCreatePresentationDto> investigations
        ) implements IRequest<Void> {
}
