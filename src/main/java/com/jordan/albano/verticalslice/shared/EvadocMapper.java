package com.jordan.albano.verticalslice.shared;

import com.jordan.albano.verticalslice.domain.Answer;
import com.jordan.albano.verticalslice.domain.ExtensionEntity;
import com.jordan.albano.verticalslice.domain.InvestigationEntity;
import com.jordan.albano.verticalslice.features.presentations.create.dto.AnswerToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.ExtensionToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.presentations.create.dto.InvestigationToCreatePresentationDto;
import com.jordan.albano.verticalslice.features.users.findall.dto.FindAllUserByPageDto;
import com.jordan.albano.verticalslice.features.users.findbyid.dto.FindUserByIdDto;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EvadocMapper {
    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "lastname", source = "lastName")
    FindAllUserByPageDto toFindAllUserByPageDTO(UserRepresentation userRepresentation);
    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "lastname", source = "lastName")
    FindUserByIdDto toFindUserById(UserRepresentation representation);
    @Mapping(target = "id", source = "id")
    Answer toAnswer(AnswerToCreatePresentationDto answerToCreatePresentationDto);

    ExtensionEntity toExtension(ExtensionToCreatePresentationDto extensionToCreatePresentationDto);

    InvestigationEntity toInvestigation(InvestigationToCreatePresentationDto investigationToCreatePresentationDto);
}
