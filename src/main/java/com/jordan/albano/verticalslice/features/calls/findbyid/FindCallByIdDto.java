package com.jordan.albano.verticalslice.features.calls.findbyid;

import com.jordan.albano.verticalslice.domain.CallState;
import com.jordan.albano.verticalslice.domain.PeriodEntity;

import java.time.LocalDate;
import java.util.List;

public record FindCallByIdDto(
        String id,
        String name,
        CallState state,
        int academicYear,
        LocalDate startDate,
        LocalDate endDate,
        List<SurveyForFindByCallIdDto> surveys
) {
}
