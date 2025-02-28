package com.jordan.albano.verticalslice.features.presentations.findallbypage;

import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;
import org.springframework.data.domain.Page;

public record FindAllPresentationsByPageQuery(
        CriteriaDTO criteria
) implements IRequest<Page<FindAllPresentationsByPageDto>> {
}
