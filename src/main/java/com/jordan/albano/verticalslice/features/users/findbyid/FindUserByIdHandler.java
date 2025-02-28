package com.jordan.albano.verticalslice.features.users.findbyid;

import com.jordan.albano.verticalslice.exceptions.UserNotFoundException;
import com.jordan.albano.verticalslice.features.users.findbyid.dto.FindUserByIdDto;
import com.jordan.albano.verticalslice.features.users.findbyid.dto.FindUserByIdQuery;
import com.jordan.albano.verticalslice.shared.EvadocMapper;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Handler
@Service
public class FindUserByIdHandler {
    private final UsersResource usersResource;
    private final EvadocMapper mapper;

    FindUserByIdHandler(UsersResource usersResource, EvadocMapper mapper) {
        this.usersResource = usersResource;
        this.mapper = mapper;
    }

    @Transactional
    public FindUserByIdDto findById(FindUserByIdQuery query) {
        try {
            return mapper.toFindUserById(usersResource.get(query.id().toString()).toRepresentation());
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }
}