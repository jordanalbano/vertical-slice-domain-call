package com.jordan.albano.verticalslice.features.users.create;

import com.jordan.albano.verticalslice.features.users.create.exceptions.EmailAlreadyExistsException;
import com.jordan.albano.verticalslice.features.users.create.exceptions.UsernameAlreadyExistsException;
import com.jordan.albano.verticalslice.exceptions.EmptyException;
import com.jordan.albano.verticalslice.shared.mediator.Handler;
import com.jordan.albano.verticalslice.shared.mediator.Validator;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static com.jordan.albano.verticalslice.features.users.create.utils.USER_ACTIONS.UPDATE_PASSWORD;
import static com.jordan.albano.verticalslice.features.users.create.utils.USER_ACTIONS.VERIFY_EMAIL;

@Handler
@Service
public class CreateUserHandler {
    private final UsersResource usersResource;

    public CreateUserHandler(UsersResource usersResource) {
        this.usersResource = usersResource;
    }

    void create(CreateUserCommand command) {
        var representation = new UserRepresentation();
        representation.setEmail(command.email());
        representation.setUsername(command.email());
        representation.setFirstName(command.name());
        representation.setLastName(command.lastname());
        representation.setAttributes(Map.of("password", List.of(command.password())));
        representation.setRequiredActions(List.of(UPDATE_PASSWORD.toString(), VERIFY_EMAIL.toString()));
        representation.setEnabled(true);
        try (var res = usersResource.create(representation)) {
            if (!Objects.equals(res.getStatus(), 200)) {
                throw new EmptyException("Error creating user");
            }
        } catch (Exception e) {
            throw new EmptyException("Error creating user");
        }
    }

    @Validator
    class createUserValidator {
        void validate(CreateUserCommand command) {
            if (!usersResource.searchByEmail(command.email(), true).isEmpty()) {
                throw new EmailAlreadyExistsException();
            }
            if (!usersResource.searchByUsername(command.username(), true).isEmpty()) {
                throw new UsernameAlreadyExistsException();
            }
        }
    }
}

