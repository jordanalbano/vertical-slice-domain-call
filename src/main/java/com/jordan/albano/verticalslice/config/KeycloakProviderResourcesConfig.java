package com.jordan.albano.verticalslice.config;

import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakProviderResourcesConfig {
    private final String clientId;
    private final String clientSecret;
    private final String authorizationGrantType;
    private final String realm;
    private final String authServerUrl;

    public KeycloakProviderResourcesConfig(
            @Value("${spring.security.oauth2.client.registration.keycloak.client-id}") String client_id,
            @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}") String client_secret,
            @Value("${spring.security.oauth2.client.registration.keycloak.authorization-grant-type}") String authorizationGrantType,
            @Value("${spring.springdoc.swagger-ui.oauth.realm}") String realm,
            @Value("${keycloak.auth-server-url}") String authServerUrl
    ) {
        clientId = client_id;
        clientSecret = client_secret;
        this.authorizationGrantType = authorizationGrantType;
        this.realm = realm;
        this.authServerUrl = authServerUrl;
    }

    private RealmResource getRealmResource() {
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .grantType(authorizationGrantType)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build().
                realm(realm);
    }
    @Bean
    public UsersResource getUsersResource() {
        RealmResource realmResource = this.getRealmResource();
        return realmResource.users();
    }

    @Bean
    public GroupsResource getGroupsResource() {
        RealmResource realmResource = this.getRealmResource();
        return realmResource.groups();
    }
}
