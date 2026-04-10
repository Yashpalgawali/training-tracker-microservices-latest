package com.example.gatewayserver;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	RouteLocator trainingTrackerRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes() 
				.route(p-> p.path("/trainingtracker/company/**")
						   .filters(f -> f.rewritePath("/trainingtracker/company/(?<segment>.*)", "/${segment}")
								   		.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								   		.retry(retryConfig->retryConfig.setRetries(5).setMethods(HttpMethod.GET).setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true))
							)
                        .uri("lb://COMPANY")
             )
			.route(p-> p.path("/trainingtracker/department/**")
					   .filters(f -> f.rewritePath("/trainingtracker/department/(?<segment>.*)", "/${segment}")
							   .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
							   .circuitBreaker(config->config.setName("companyCircuitBreaker").setFallbackUri("forward:/contactSupport"))
							   
							   )
                    .uri("lb://DEPARTMENT")
             ).build();
		
	}
}
