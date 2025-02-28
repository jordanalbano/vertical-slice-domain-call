package com.jordan.albano.verticalslice.features.calls.create;


import com.jordan.albano.verticalslice.domain.CallState;
import com.jordan.albano.verticalslice.domain.PeriodEntity;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;

import java.util.List;

/**
 * usecase for {@link com.jordan.albano.verticalslice.domain.CallEntity}
 */
public record CreateCallCommand(
        int academicYear,
        PeriodEntity period,
        CallState callState,
        List<SurveyIdOfCreateCallDto> surveys

) implements IRequest<Void> {
}
