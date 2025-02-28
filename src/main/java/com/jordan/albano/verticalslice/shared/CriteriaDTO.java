package com.jordan.albano.verticalslice.shared;

import jakarta.validation.constraints.Min;
import lombok.Builder;

import java.util.List;
@Builder
public record CriteriaDTO(
    List<SearchCriteriaDTO> filters,
    @Min(0)
    int page,
    @Min(1)
    int size,
    List<SortDTO> orders) {

}
