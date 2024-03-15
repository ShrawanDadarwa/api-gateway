package com.gateway.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomRoutePredicateFactory implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
       log.info("WebFilter works as an interceptor , you can intercept your request ",exchange.getAttributes());
        return chain.filter(exchange);
    }
}
