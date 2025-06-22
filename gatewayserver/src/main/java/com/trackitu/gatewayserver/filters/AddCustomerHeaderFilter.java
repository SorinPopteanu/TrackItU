package com.trackitu.gatewayserver.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AddCustomerHeaderFilter extends AbstractGatewayFilterFactory<Object> {


  @Override
  public GatewayFilter apply(Object config) {
    return (exchange, chain) -> ReactiveSecurityContextHolder.getContext()
        .map(SecurityContext::getAuthentication)
        .switchIfEmpty(Mono.empty())
        .flatMap(authentication -> {
          if (authentication instanceof JwtAuthenticationToken jwtAuth && jwtAuth.isAuthenticated()) {
            String username = jwtAuth.getToken().getClaimAsString("preferred_username");
            ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-User-Name", username)
                .build();
            ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();
            return chain.filter(mutatedExchange);
          }
          return chain.filter(exchange);
        });
  }
}
