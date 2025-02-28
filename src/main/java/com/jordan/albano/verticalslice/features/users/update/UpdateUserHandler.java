package com.jordan.albano.verticalslice.features.users.update;

import com.jordan.albano.verticalslice.features.users.findall.ExistsUserByIdQuery;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.keycloak.admin.client.resource.UsersResource;

@Handler
class UpdateUserHandler {
    private UsersResource usersResource;
    private final JMediator mediator;

    UpdateUserHandler(UsersResource usersResource, JMediator mediator) {
        this.usersResource = usersResource;
        this.mediator = mediator;
    }

    void update(UpdateUserCommand command) {
        var userResource = this.usersResource.get(command.id().toString());
        var representation = userResource.toRepresentation();
        representation.setFirstName(command.name());
        representation.setLastName(command.lastname());
        userResource.update(representation);
    }

    @Validator
    class UpdateUserValidator {
        void validate(UpdateUserCommand command) throws Throwable {
            if (!mediator.send(new ExistsUserByIdQuery(command.id()))) {
                throw new IllegalArgumentException("el usuario no existe");
            }
        }
    }
}
