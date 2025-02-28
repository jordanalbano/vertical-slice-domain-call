package com.jordan.albano.verticalslice.features.presentations.create;

import com.jordan.albano.verticalslice.domain.Answer;
import com.jordan.albano.verticalslice.domain.ExtensionEntity;
import com.jordan.albano.verticalslice.domain.InvestigationEntity;
import com.jordan.albano.verticalslice.features.presentations.create.dto.AnswerToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.ExtensionToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.InvestigationToCreatePresentationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface CreatePresentationMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "presentation.id", source = "questionId")
    Answer toAnswer(AnswerToCreatePresentationDto answerToCreatePresentationDto);

    ExtensionEntity toExtension(ExtensionToCreatePresentationDto extensionToCreatePresentationDto);

    InvestigationEntity toInvestigation(InvestigationToCreatePresentationDto investigationToCreatePresentationDto);
}
