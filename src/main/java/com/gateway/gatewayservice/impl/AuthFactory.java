package com.gateway.gatewayservice.impl;

import com.gateway.gatewayservice.constants.AuthType;
import com.gateway.gatewayservice.impl.BasicAuth;
import com.gateway.gatewayservice.models.Auth;
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
