package com.gateway.gatewayservice.configs;

import com.gateway.gatewayservice.models.Auth;
import com.gateway.gatewayservice.models.AuthCredential;
import com.gateway.gatewayservice.impl.AuthFactory;
import com.gateway.gatewayservice.constants.AuthType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AllRoutes allRoutes;
    @Autowired
    private AuthFactory authFactory;
    @Value("${SECURITY-TOKEN}")
    private String token;

    @Bean
    public RouteLocator routeLocatorProvider(final RouteLocatorBuilder builder) {
        final RouteLocatorBuilder.Builder routeBuilder = builder.routes();

        allRoutes.getRouteList().forEach(route -> {
            routeBuilder.route(route.getRouteId(), r -> r
                            .path(String.format("/%s/%s/**", token, route.getRoutePrefix()))
                            .filters(filter -> filter
                                    .stripPrefix(2)
                                    .addRequestHeader("Authorization", getAuthValues(route.getCredential())
                            )
                    ).uri(route.getDestinationURI())
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
