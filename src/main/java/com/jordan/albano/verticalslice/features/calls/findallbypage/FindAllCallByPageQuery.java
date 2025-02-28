package com.jordan.albano.verticalslice.features.calls.findallbypage;

import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;
import org.springframework.data.domain.Page;

public record FindAllCallByPageQuery(
        CriteriaDTO criteria
) implements IRequest<Page<FindAllCallsByPageDto>> {
}
