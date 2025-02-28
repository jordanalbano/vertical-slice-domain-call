package com.jordan.albano.verticalslice.features.presentations.findallbypage;

import com.jordan.albano.verticalslice.domain.PresentationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FindAllPresentationMapper {
    @Mapping(target = "id", source = "id" )
    @Mapping(target = "callToApply.academicYear", source = "callToApply.academicYear")
    FindAllPresentationsByPageDto convert(PresentationEntity query);
}
