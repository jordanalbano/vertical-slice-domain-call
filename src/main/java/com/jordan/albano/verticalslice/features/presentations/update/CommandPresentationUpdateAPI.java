package com.jordan.albano.verticalslice.features.presentations.update;

import com.jordan.albano.verticalslice.shared.CommandAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(CommandAPIPath.CPRESENTATION_MAPPING)
class CommandPresentationUpdateAPI {

    private final JMediator mediator;

    public CommandPresentationUpdateAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @PutMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "actualizar los datos de una presentacion", security = @SecurityRequirement(name = "bearerAuth"))
    void create(@RequestBody UpdatePresentationCommand command) throws Throwable {
        mediator.send(command);
    }
}
