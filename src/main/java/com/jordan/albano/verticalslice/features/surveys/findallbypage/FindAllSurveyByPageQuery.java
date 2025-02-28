package com.jordan.albano.verticalslice.features.surveys.findallbypage;

import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import com.jordan.albano.verticalslice.shared.SearchCriteriaDTO;
import com.jordan.albano.verticalslice.shared.SortDTO;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;

import java.util.List;

public record FindAllSurveyByPageQuery(
        List<SearchCriteriaDTO> filters,
        @Min(0)
        int page,
        @Min(1)
        int size,
        List<SortDTO> orders
) implements IRequest<Page<FindAllSurveyByPageDto>> {
}
