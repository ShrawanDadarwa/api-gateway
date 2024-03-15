package com.gateway.apigateway.config;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NoArgsConstructor
public class CustomConfiguration {

    private  RequestFilter requestFilter;
    private  GlobalFilters webFilter;

    CustomConfiguration(RequestFilter requestFilter,GlobalFilters webFilter){
        this.requestFilter = requestFilter;
        this.webFilter = webFilter;
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service",r -> r.path("/products")
                        .and().method("POST")
                        /*.and().readBody(Student.class, s -> true)*/.filters(f -> f.filters(requestFilter,webFilter))
                        .uri("http://localhost:8082"))
                /*.route("first-microservice",r -> r.path("/first")
                        .and().method("GET").filters(f-> f.filters(requestFilter,webFilter))
                        .uri("http://localhost:8081"))*/
                // This is the routing service
                .route("auth-server",r -> r.path("/login")
                        .uri("http://localhost:8088"))
                .build();
    }
}
