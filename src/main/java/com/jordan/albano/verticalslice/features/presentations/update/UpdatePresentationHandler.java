package com.jordan.albano.verticalslice.features.presentations.update;

import com.jordan.albano.verticalslice.domain.CallEntity;
import com.jordan.albano.verticalslice.exceptions.BadRequestException;
import com.jordan.albano.verticalslice.features.presentations.JpaPresentationRepository;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Handler
@Service
public class UpdatePresentationHandler {
    private final JpaPresentationRepository jpaPresentationRepository;
    private final UpdatePresentationMapper mapper;

    public UpdatePresentationHandler(
            JpaPresentationRepository jpaPresentationRepository,
            UpdatePresentationMapper mapper) {
        this.jpaPresentationRepository = jpaPresentationRepository;
        this.mapper = mapper;
    }

    public void update(UpdatePresentationCommand command) {
        var entity = this.jpaPresentationRepository.findById(command.id())
                .orElseThrow(() -> new BadRequestException("Presentación no encontrada"));
        entity.setAnswers(command.answers().stream().map(mapper::toAnswer).collect(Collectors.toSet()));
        entity.setCallToApply(new CallEntity(command.callToApply().id()));
        entity.setExtensions(command.extensions().stream().map(mapper::toExtension).collect(Collectors.toSet()));
        entity.setInvestigations(command.investigations().stream().map(mapper::toInvestigation).collect(Collectors.toSet()));
        this.jpaPresentationRepository.save(entity);
    }

    @Validator
    private static class UpdatePresentationValidator {
        void validate(UpdatePresentationCommand command) {
            if (Objects.isNull(command.userId())) {
                throw new BadRequestException("Debe ingresar un usuario");
            }
            if (Objects.isNull(command.callToApply().id())) {
                throw new BadRequestException("Debe ingresar una convocatoria");
            }
        }
    }
}

