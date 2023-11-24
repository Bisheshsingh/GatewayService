package com.gateway.gatewayservice.models;

import com.gateway.gatewayservice.impl.BasicAuth;
import org.springframework.stereotype.Component;

@Component
public class AuthFactory {
    public Auth getAuth(final AuthType type) {
        return switch (type) {
            case BASIC_AUTH -> new BasicAuth();
            default -> null;
        };
    }
}
