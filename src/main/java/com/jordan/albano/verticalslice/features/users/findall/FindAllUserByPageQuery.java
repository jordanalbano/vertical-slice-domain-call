package com.jordan.albano.verticalslice.features.users.findall;

import com.jordan.albano.verticalslice.features.users.findall.dto.FindAllUserByPageDto;
import com.jordan.albano.verticalslice.shared.mediator.IRequest;
import org.springframework.data.domain.Page;

public record FindAllUserByPageQuery(
) implements IRequest<Page<FindAllUserByPageDto>> {
}
