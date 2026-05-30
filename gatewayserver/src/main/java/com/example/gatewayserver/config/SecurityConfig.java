package com.example.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
		
		 serverHttpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
				 		.anyExchange().permitAll()
//																.pathMatchers("/trainingtracker/company/**").permitAll()
//																.pathMatchers("/trainingtracker/department/**").authenticated()
//																.pathMatchers("/trainingtracker/designation/**").authenticated()																	
										 	).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()));
		serverHttpSecurity.csrf(csrf-> csrf.disable());
		return (SecurityFilterChain) serverHttpSecurity.build();
	}
}
