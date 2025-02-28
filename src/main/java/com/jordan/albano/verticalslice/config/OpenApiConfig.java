package com.jordan.albano.verticalslice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Evaluacion docente", version = "1.0"))
/*
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(implicit = @OAuthFlow(authorizationUrl = "${springdoc.oauth2.authorization-url}") ))
*/
public class OpenApiConfig {
}
