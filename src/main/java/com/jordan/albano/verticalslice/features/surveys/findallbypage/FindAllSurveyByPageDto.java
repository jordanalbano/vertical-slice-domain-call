package com.jordan.albano.verticalslice.features.surveys.findallbypage;

import com.jordan.albano.verticalslice.domain.UserTypeObjective;

import java.time.LocalDate;

public record FindAllSurveyByPageDto(
        String id,
        String name,
        LocalDate createdOn,
        UserTypeObjective userType
) {
}
