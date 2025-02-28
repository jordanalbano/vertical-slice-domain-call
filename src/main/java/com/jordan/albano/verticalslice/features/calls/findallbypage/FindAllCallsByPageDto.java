package com.jordan.albano.verticalslice.features.calls.findallbypage;


import com.jordan.albano.verticalslice.domain.CallState;

public record FindAllCallsByPageDto(
        String id,
        String name,
        String academicYear,
        CallState state

) {
}
