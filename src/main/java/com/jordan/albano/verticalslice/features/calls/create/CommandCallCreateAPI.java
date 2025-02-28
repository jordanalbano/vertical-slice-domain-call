package com.jordan.albano.verticalslice.features.calls.create;

import com.jordan.albano.verticalslice.shared.CommandAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(CommandAPIPath.CCALL_MAPPING)
class CommandCallCreateAPI {

    private final JMediator mediator;

    public CommandCallCreateAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Crear una convocatoria", security = @SecurityRequirement(name = "bearerAuth"))
    void create(@RequestBody CreateCallCommand command) throws Throwable {
        mediator.send(command);
    }
}
