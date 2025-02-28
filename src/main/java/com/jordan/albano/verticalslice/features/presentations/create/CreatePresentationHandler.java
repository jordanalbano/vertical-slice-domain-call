package com.jordan.albano.verticalslice.features.presentations.create;

import com.jordan.albano.verticalslice.domain.CallEntity;
import
        com.jordan.albano.verticalslice.domain.PresentationEntity;
import com.jordan.albano.verticalslice.exceptions.BadRequestException;
import com.jordan.albano.verticalslice.features.presentations.JpaPresentationRepository;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Handler
@Service
@Repository
public class CreatePresentationHandler {
    private final JpaPresentationRepository jpaPresentationRepository;
    private final CreatePresentationMapper mapper;

    protected CreatePresentationHandler(
            JpaPresentationRepository jpaPresentationRepository,
            CreatePresentationMapper mapper) {
        this.jpaPresentationRepository = jpaPresentationRepository;
        this.mapper = mapper;
    }

    public void create(CreatePresentationCommand command) {
        var entity = new PresentationEntity();
        entity.setUserId(command.userId());
        entity.setCallToApply(new CallEntity(command.callToApply().id()));
        entity.setAnswers(command.answers().stream().map(mapper::toAnswer).collect(Collectors.toSet()));
        entity.setExtensions(command.extensions().stream().map(mapper::toExtension).collect(Collectors.toSet()));
        entity.setInvestigations(command.investigations().stream().map(mapper::toInvestigation).collect(Collectors.toSet()));
        this.jpaPresentationRepository.save(entity);
    }

    @Validator
    private static class CreatePresentationValidator {
        void validate(CreatePresentationCommand command) {
            if (Objects.isNull(command.userId())) {
                throw new BadRequestException("Debe ingresar un usuario");
            }
            if (Objects.isNull(command.callToApply().id())) {
                throw new BadRequestException("Debe ingresar una convocatoria");
            }
        }
    }
}

