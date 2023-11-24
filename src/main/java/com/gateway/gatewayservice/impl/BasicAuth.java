package com.gateway.gatewayservice.impl;

import com.gateway.gatewayservice.models.Auth;
import com.gateway.gatewayservice.models.AuthCredential;
import com.gateway.gatewayservice.constants.CredentialParam;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicAuth implements Auth {
    private static final String TYPE = "Basic";

    @Override
    public String encodeAuthValue(final AuthCredential credential) {
        byte[] credentials = (String.format("%s:%s",
                credential.getParam(CredentialParam.USER_NAME),
                credential.getParam(CredentialParam.PASSWORD))).getBytes(StandardCharsets.UTF_8);

        return TYPE + " " + Base64.getEncoder().encodeToString(credentials);
    }
}
