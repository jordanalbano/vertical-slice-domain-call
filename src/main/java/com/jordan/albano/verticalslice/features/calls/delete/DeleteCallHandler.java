package com.jordan.albano.verticalslice.features.calls.delete;

import com.jordan.albano.verticalslice.exceptions.BadRequestException;
import com.jordan.albano.verticalslice.features.calls.JpaCallRepository;
import com.jordan.albano.verticalslice.features.calls.findbyid.CallNotFoundException;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Handler
@Service
@Transactional
class DeleteCallHandler {
    private final JpaCallRepository jpaCallRepository;

    protected DeleteCallHandler(JpaCallRepository jpaCallRepository) {
        this.jpaCallRepository = jpaCallRepository;
    }

    protected void delete(DeleteCallCommand command) {
        var entity = this.jpaCallRepository.findById(command.id()).orElseThrow(CallNotFoundException::new);
        entity.finishLifecycle();
    }

    @Validator
    public static class DeleteCallValidator {
        void validate(DeleteCallCommand command) {
            if (Objects.isNull(command.id())) {
                throw new BadRequestException("El identificador de la convocatoria es requerido");
            }
        }
    }
}

