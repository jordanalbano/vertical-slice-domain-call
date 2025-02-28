package com.jordan.albano.verticalslice.features.calls.findallbypage;

import com.jordan.albano.verticalslice.shared.QueryAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(QueryAPIPath.QCALL_MAPPING)
public class QueryCallFindAllByPageAPI {

    private final JMediator mediator;

    public QueryCallFindAllByPageAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(OK)
    @Operation(summary = "Obtener todas las convocatorias paginadas", security = @SecurityRequirement(name = "bearerAuth"))
    protected Page<FindAllCallsByPageDto> findAllByPage(@RequestBody FindAllCallByPageQuery query) throws Throwable {
        return mediator.send(query);
    }
}
