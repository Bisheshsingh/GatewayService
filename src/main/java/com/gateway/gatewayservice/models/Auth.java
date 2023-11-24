package com.gateway.gatewayservice.models;

public interface Auth {
    String encodeAuthValue(AuthCredential credential);
}
