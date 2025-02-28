package com.jordan.albano.verticalslice.features.surveys.findallbypage;

import com.jordan.albano.verticalslice.domain.CallEntity;
import com.jordan.albano.verticalslice.domain.PresentationEntity;
import com.jordan.albano.verticalslice.domain.SurveyEntity;
import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallsByPageDto;
import com.jordan.albano.verticalslice.features.presentations.findallbypage.FindAllPresentationsByPageDto;
import com.jordan.albano.verticalslice.shared.CriteriaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FindAllSurveysByPageMapper {

    FindAllSurveyByPageDto convert(SurveyEntity entity);
    CriteriaDTO convert(FindAllSurveyByPageQuery query);
}
