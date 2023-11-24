package com.gateway.gatewayservice;

import lombok.Data;

@Data
public class Route {
    private final String routeId;
    private final String routePrefix;
    private final String destinationURI;
}
