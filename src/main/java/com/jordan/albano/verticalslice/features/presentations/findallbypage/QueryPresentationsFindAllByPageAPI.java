package com.jordan.albano.verticalslice.features.presentations.findallbypage;

import com.jordan.albano.verticalslice.shared.QueryAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(QueryAPIPath.QPRESENTATION_MAPPING)
public class QueryPresentationsFindAllByPageAPI {

    private final JMediator mediator;

    public QueryPresentationsFindAllByPageAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(OK)
    @Operation(summary = "Obtener todas las presentaciones paginadas", security = @SecurityRequirement(name = "bearerAuth"))
    Page<FindAllPresentationsByPageDto> findAllPresentationsByPage(@RequestBody FindAllPresentationsByPageQuery query) throws Throwable {
        return mediator.send(query);
    }
}
