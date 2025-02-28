package com.jordan.albano.verticalslice.features.users.findall;

import com.jordan.albano.verticalslice.shared.mediator.Handler;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Service;

@Handler
@Service
public class ExistsUserByIdHandler {

    private final UsersResource usersResource;

    ExistsUserByIdHandler(UsersResource usersResource) {
        this.usersResource = usersResource;
    }


    public Boolean exists(ExistsUserByIdQuery query) {
        try {
            usersResource.get(query.id().toString()).toRepresentation();
            return true;
        } catch (Exception e) {
            return false;
        }


    }
}