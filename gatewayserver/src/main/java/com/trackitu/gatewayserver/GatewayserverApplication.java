package com.trackitu.gatewayserver;

import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayserverApplication.class, args);
  }

  @Bean
  public RouteLocator trackItURouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder.routes()
        .route(p -> p.path("/trackitu/accounts/**")
            .filters(f -> f.rewritePath("trackitu/accounts/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
            .uri("lb://ACCOUNTS"))
        .route(p -> p.path("/trackitu/rooms/**")
            .filters(f -> f.rewritePath("trackitu/rooms/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
            .uri("lb://ROOMS"))
        .route(p -> p.path("/trackitu/assets/**")
            .filters(f -> f.rewritePath("trackitu/assets/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
            .uri("lb://ASSETS")).build();

  }
}
