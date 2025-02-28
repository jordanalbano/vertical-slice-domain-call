package com.jordan.albano.verticalslice.features.calls.findbyid;

import com.jordan.albano.verticalslice.features.calls.JpaCallRepository;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Handler
@Service
@Transactional(readOnly = true)
public class FindByIdCallHandler {
    private final JpaCallRepository repository;
    private final FindByIdMapper mapper;

    public FindByIdCallHandler(JpaCallRepository repository, FindByIdMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public FindCallByIdDto findAllByPage(FindCallByIdQuery query) {
        var presentations = repository.findByIdAndEnabledIsTrue(query.id());
        return presentations.map(mapper::convert).orElseThrow(CallNotFoundException::new);
    }

}
