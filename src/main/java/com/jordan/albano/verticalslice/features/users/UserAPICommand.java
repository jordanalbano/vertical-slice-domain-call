package com.jordan.albano.verticalslice.features.users;

import com.jordan.albano.verticalslice.features.users.create.CreateUserCommand;
import com.jordan.albano.verticalslice.features.users.delete.DeleteUserCommand;
import com.jordan.albano.verticalslice.features.users.update.UpdateUserCommand;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.jordan.albano.verticalslice.shared.CommandAPIPath.CUSER_MAPPING;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(CUSER_MAPPING)
class UserAPICommand {

    JMediator mediator;

    public UserAPICommand(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Crear un usuario", security = @SecurityRequirement(name = "bearerAuth"))
    void add(@RequestBody CreateUserCommand command) throws Throwable {
        mediator.send(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    void update(@PathVariable("id") UUID id, UpdateUserCommand command) throws Throwable {
        mediator.send(command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    void delete(@PathVariable("id") UUID id) throws Throwable {
        mediator.send(new DeleteUserCommand(id));
    }

}
