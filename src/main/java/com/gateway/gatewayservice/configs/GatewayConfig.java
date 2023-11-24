package com.gateway.gatewayservice.configs;

import com.gateway.gatewayservice.models.Auth;
import com.gateway.gatewayservice.models.AuthCredential;
import com.gateway.gatewayservice.impl.AuthFactory;
import com.gateway.gatewayservice.constants.AuthType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GatewayConfig {
    private AllRoutes allRoutes;
    private AuthFactory authFactory;

    @Bean
    public RouteLocator customRouteLocator(final RouteLocatorBuilder builder) {
        final RouteLocatorBuilder.Builder routeBuilder = builder.routes();

        allRoutes.getRouteList().forEach(route -> {
            routeBuilder.route(route.getRouteId() + "_post", r -> r
                            .path(String.format("/%s/**", route.getRoutePrefix()))
                            .filters(filter -> filter
                                    .stripPrefix(1)
                                    .addRequestHeader("Authorization", getAuthValues(route.getCredential())
                            )
                    ).method("POST")           
                    .uri(route.getDestinationURI())
            ).route(route.getRouteId() + "_get", r -> r
                            .path(String.format("/%s/**", route.getRoutePrefix()))
                            .filters(filter -> filter
                                    .stripPrefix(1)
                                    .addRequestHeader("Authorization", getAuthValues(route.getCredential())
                            )
                    ).method("GET")           
                    .uri(route.getDestinationURI())
            );
        });

        return routeBuilder.build();
    }

    private  String getAuthValues(final AuthCredential credential) {
        if(credential.getType().equals(AuthType.NO_AUTH)) {
            return "";
        }

        final Auth auth = authFactory.getAuth(credential.getType());

        return auth.encodeAuthValue(credential);
    }
}
