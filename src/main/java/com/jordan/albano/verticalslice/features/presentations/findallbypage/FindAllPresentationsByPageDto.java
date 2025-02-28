package com.jordan.albano.verticalslice.features.presentations.findallbypage;

import com.jordan.albano.verticalslice.domain.PresentationState;

public record FindAllPresentationsByPageDto(
        String id,
        PresentationState state,
        CallToApplyOfFindAllPresentationsByPageDto callToApply


) {
}
