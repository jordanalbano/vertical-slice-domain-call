package com.jordan.albano.verticalslice.features.calls.create;


import com.jordan.albano.verticalslice.domain.CallEntity;
import com.jordan.albano.verticalslice.domain.SurveyEntity;
import com.jordan.albano.verticalslice.exceptions.BadRequestException;
import com.jordan.albano.verticalslice.features.calls.JpaCallRepository;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Handler
@Service
class CreateCallHandler {
    private final JpaCallRepository jpaCallRepository;
    private final String prefixCall;

    protected CreateCallHandler(
            JpaCallRepository jpaCallRepository
    ) {
        this.jpaCallRepository = jpaCallRepository;
        this.prefixCall = "Convocatoria para el ciclo lectivo ";
    }

    protected void create(CreateCallCommand command) {
        var entity = new CallEntity();
        entity.setName(prefixCall + command.academicYear());
        entity.setAcademicYear(command.academicYear());
        entity.setPeriod(command.period());
        entity.setState(command.callState());
        entity.setSurveys(command.surveys().stream().map(this::createSurvey).collect(Collectors.toSet()));
        this.jpaCallRepository.save(entity);
    }

    private SurveyEntity createSurvey(SurveyIdOfCreateCallDto survey) {
        var surveyEntity = new SurveyEntity();
        surveyEntity.setId(survey.id());
        return surveyEntity;
    }

    @Validator
    public static class CreateCallValidator {
        void validate(CreateCallCommand command) {
            if (command.period().getEndDate().isBefore(command.period().getStartDate()) ||
                    command.period().getEndDate().isEqual(command.period().getStartDate())) {
                throw new BadRequestException("El periodo de fin no puede ser anterior al periodo de inicio");
            }
        }
    }
}

