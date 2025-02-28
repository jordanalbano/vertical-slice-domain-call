package com.jordan.albano.verticalslice.features.presentations.delete;

import com.jordan.albano.verticalslice.exceptions.BadRequestException;
import com.jordan.albano.verticalslice.features.presentations.JpaPresentationRepository;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Handler
@Service
@Transactional
public class DeletePresentationHandler {
    protected final JpaPresentationRepository jpaPresentationRepository;

    public DeletePresentationHandler(JpaPresentationRepository jpaPresentationRepository) {
        this.jpaPresentationRepository = jpaPresentationRepository;
    }

    public void delete(DeletePresentationCommand command) {
        var entity = this.jpaPresentationRepository.findById(command.id())
                .orElseThrow(() -> new BadRequestException("Presentación no encontrada"));
        entity.finishLifecycle();
        this.jpaPresentationRepository.save(entity);
    }
}

