package com.lypgod.itemsharing.gatewayserver.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author lypgod
 */
@Component
@Log4j2
public class TrackingFilter extends AbstractGatewayFilterFactory<TrackingFilter.Config> {
    private static final String CORRELATION_ID = "is-correlation-id";

    public TrackingFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (request.getHeaders().containsKey(CORRELATION_ID)) {
                log.warn("TrackingFilter got a Correlation-Id: {}", request.getHeaders().get(CORRELATION_ID));
            } else {
                String newCorrelationId = UUID.randomUUID().toString();
                ServerHttpRequest modifiedRequest = request.mutate().
                        header(CORRELATION_ID, new String[]{newCorrelationId}).
                        build();
                log.warn("TrackingFilter generated new Correlation-Id: {}", newCorrelationId);
                return chain.filter(exchange.mutate().request(modifiedRequest).build());
            }

            return chain.filter(exchange);
        };
    }

    static class Config {
        // Put the configuration properties
    }
}