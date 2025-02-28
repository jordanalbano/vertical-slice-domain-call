package com.jordan.albano.verticalslice.features.presentations.findallbypage;

import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Handler
@Service
public class FindAllPresentationsByPageHandler {
    private final FindAllPresentationMapper mapper;
    private final SearchPresentationRepository repository;

    FindAllPresentationsByPageHandler(FindAllPresentationMapper mapper, SearchPresentationRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<FindAllPresentationsByPageDto> findAllByPage(FindAllPresentationsByPageQuery query) {
        var presentations = repository.findByPage(query.criteria());
        return presentations.map(mapper::convert);
    }
}