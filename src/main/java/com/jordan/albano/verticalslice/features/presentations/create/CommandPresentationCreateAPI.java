package com.jordan.albano.verticalslice.features.presentations.create;

import com.jordan.albano.verticalslice.shared.CommandAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(CommandAPIPath.CPRESENTATION_MAPPING)
class CommandPresentationCreateAPI {

    private final JMediator mediator;

    public CommandPresentationCreateAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Crear una presentacion", security = @SecurityRequirement(name = "bearerAuth"))
    void create(@RequestBody CreatePresentationCommand command) throws Throwable {
        mediator.send(command);
    }
}
