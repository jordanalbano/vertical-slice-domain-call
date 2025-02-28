package com.jordan.albano.verticalslice.shared;

import lombok.With;

@With
public record SearchCriteriaDTO(
        String attr,
        Object value,
        SearchOperation searchOperation
) {
}

