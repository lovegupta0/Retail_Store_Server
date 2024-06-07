package com.retailStore.clientserver.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RouteConfig {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes().route(p->p.path("/user/**").uri("lb://USER-SERVICE"))
                .route(p->p.path("/getUID").uri("lb://UID-SERVICE"))
                .build();
    }
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
