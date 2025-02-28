package com.jordan.albano.verticalslice.features.surveys.findallbypage;

import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallByPageQuery;
import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallMapper;
import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallsByPageDto;
import com.jordan.albano.verticalslice.features.calls.findallbypage.SearchCallRepository;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Handler
@Service
@Transactional(readOnly = true)
public class FindAllSurveysByPageHandler {
    private final FindAllSurveysByPageMapper mapper;
    private final SearchSurveysByPageRepository repository;

    FindAllSurveysByPageHandler(FindAllSurveysByPageMapper mapper, SearchSurveysByPageRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<FindAllSurveyByPageDto> findAllByPage(FindAllSurveyByPageQuery query) {
        var presentations = repository.findByPage(mapper.convert(query));
        return presentations.map(mapper::convert);
    }
}