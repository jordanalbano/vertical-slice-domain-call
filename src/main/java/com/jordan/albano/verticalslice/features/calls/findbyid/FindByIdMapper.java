package com.jordan.albano.verticalslice.features.calls.findbyid;

import com.jordan.albano.verticalslice.domain.CallEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FindByIdMapper {
    @Mapping(target = "startDate", source = "period.startDate")
    @Mapping(target = "endDate", source = "period.endDate")
    FindCallByIdDto convert(CallEntity callEntity);
}
