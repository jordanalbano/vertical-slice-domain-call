package com.jordan.albano.verticalslice.features.calls.delete;

import com.jordan.albano.verticalslice.shared.CommandAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(CommandAPIPath.CCALL_MAPPING)
class CommandDeleteCreateAPI {

    private final JMediator mediator;

    public CommandDeleteCreateAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "Se debe eliminar una convocatoria satistactoriamente", security = @SecurityRequirement(name = "bearerAuth"))
    void create(@PathVariable String id) throws Throwable {
        mediator.send(new DeleteCallCommand(UUID.fromString(id)));
    }
}
