package com.gateway.gatewayservice.models;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(value = "routes")
public class AllRoutes {
    private List<Route> routeList = new LinkedList<>();
}
