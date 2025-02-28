package com.jordan.albano.verticalslice.features.surveys.findallbypage;

import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallByPageQuery;
import com.jordan.albano.verticalslice.features.calls.findallbypage.FindAllCallsByPageDto;
import com.jordan.albano.verticalslice.shared.QueryAPIPath;
import com.jordan.albano.verticalslice.shared.mediator.JMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(QueryAPIPath.QSURVEY_MAPPING)
public class QuerySurveysFindAllByPageAPI {

    private final JMediator mediator;

    public QuerySurveysFindAllByPageAPI(JMediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    @ResponseStatus(OK)
    @Operation(summary = "Obtener todas las encuestas paginadas", security = @SecurityRequirement(name = "bearerAuth"))
    protected Page<FindAllSurveyByPageDto> findAllByPage(@RequestBody FindAllSurveyByPageQuery query) throws Throwable {
        return mediator.send(query);
    }
}
