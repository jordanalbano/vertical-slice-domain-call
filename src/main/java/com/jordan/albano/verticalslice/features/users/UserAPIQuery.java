package com.jordan.albano.verticalslice.features.users;

import com.jordan.albano.verticalslice.features.users.findall.dto.FindAllUserByPageDto;
import com.jordan.albano.verticalslice.features.users.findbyid.dto.FindUserByIdDto;
import com.jordan.albano.verticalslice.features.users.findall.FindAllUserByPageQuery;
import com.jordan.albano.verticalslice.features.users.findbyid.dto.FindUserByIdQuery;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/query-users")
class UserAPIQuery {
    private final JMediator mediator;

    public UserAPIQuery(JMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping
    @ResponseStatus(OK)
    Page<FindAllUserByPageDto> list() throws Throwable {
        return mediator.send(new FindAllUserByPageQuery());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    FindUserByIdDto get(@PathVariable("id") UUID id) throws Throwable {
        return mediator.send(new FindUserByIdQuery(id));
    }

}
