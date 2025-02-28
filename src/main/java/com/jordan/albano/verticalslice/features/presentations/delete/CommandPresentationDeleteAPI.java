package com.jordan.albano.verticalslice.features.presentations.delete;

import com.jordan.albano.verticalslice.shared.CommandAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(CommandAPIPath.CPRESENTATION_MAPPING)
class CommandPresentationDeleteAPI {

    private final JMediator mediator;

    public CommandPresentationDeleteAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "actualizar los datos de una presentacion", security = @SecurityRequirement(name = "bearerAuth"))
    void create(@PathVariable UUID id) throws Throwable {
        mediator.send(new DeletePresentationCommand(id));
    }
}
