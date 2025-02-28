package com.jordan.albano.verticalslice.features.users.delete;

import com.jordan.albano.verticalslice.features.users.findall.ExistsUserByIdQuery;
import com.jordan.albano.verticalslice.features.users.create.CreateUserCommand;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.keycloak.admin.client.resource.UsersResource;

@Handler
public class DeleteUserHandler {
    private final UsersResource usersResource;
    private final JMediator mediator;

    public DeleteUserHandler(UsersResource usersResource, JMediator mediator) {
        this.usersResource = usersResource;
        this.mediator = mediator;
    }

    void create(DeleteUserCommand command) {
        var userResource = this.usersResource.get(command.id().toString());
        userResource.groups().stream().filter(r -> r.getName().contains("evadoc-")).forEach(r -> userResource.leaveGroup(r.getId()));
    }

    @Validator
    class createUserValidator {
        void validate(CreateUserCommand command) throws Throwable {
            if (mediator.send(new ExistsUserByIdQuery(command.id()))) {
                throw new IllegalArgumentException("el usuario ya existe");
            }
        }
    }
}
