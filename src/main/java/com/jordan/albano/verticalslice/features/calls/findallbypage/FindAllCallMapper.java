package com.jordan.albano.verticalslice.features.calls.findallbypage;

import com.jordan.albano.verticalslice.domain.CallEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FindAllCallMapper {
    @Mapping(target = "id", source = "id")
    FindAllCallsByPageDto convert(CallEntity query);
}
