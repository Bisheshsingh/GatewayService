package com.gateway.gatewayservice.models;

import com.gateway.gatewayservice.constants.AuthType;
import com.gateway.gatewayservice.constants.CredentialParam;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AuthCredential {
    private AuthType type;
    private Map<CredentialParam, String> params;

    public AuthCredential() {
        this.type = AuthType.NO_AUTH;
        this.params = new HashMap<>();
    }

    public String getParam(final CredentialParam param) {
        return params.get(param);
    }
}
