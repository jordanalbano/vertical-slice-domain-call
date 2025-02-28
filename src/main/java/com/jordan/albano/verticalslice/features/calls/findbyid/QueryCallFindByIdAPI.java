package com.jordan.albano.verticalslice.features.calls.findbyid;

import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallByPageQuery;
import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallsByPageDto;
import com.jordan.albano.verticalslice.shared.QueryAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(QueryAPIPath.QCALL_MAPPING)
public class QueryCallFindByIdAPI {

    private final JMediator mediator;

    public QueryCallFindByIdAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Obtener una convocatoria por id", security = @SecurityRequirement(name = "bearerAuth"))
    protected FindCallByIdDto findAllByPage(@PathVariable String id) throws Throwable {
        return mediator.send(new FindCallByIdQuery(UUID.fromString(id)));
    }
}
