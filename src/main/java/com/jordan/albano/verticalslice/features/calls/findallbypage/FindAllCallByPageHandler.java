package com.jordan.albano.verticalslice.features.calls.findallbypage;

import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Handler
@Service
@Transactional(readOnly = true)
public class FindAllCallByPageHandler {
    private final FindAllCallMapper mapper;
    private final SearchCallRepository repository;

    FindAllCallByPageHandler(FindAllCallMapper mapper, SearchCallRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<FindAllCallsByPageDto> findAllByPage(FindAllCallByPageQuery query) {
        var presentations = repository.findByPage(query.criteria());
        return presentations.map(mapper::convert);
    }
}