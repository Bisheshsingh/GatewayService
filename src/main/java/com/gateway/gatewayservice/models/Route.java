package com.gateway.gatewayservice.models;

import lombok.Data;

@Data
public class Route {
    private String routeId;
    private String routePrefix;
    private String destinationURI;
    private AuthCredential credential;
}
