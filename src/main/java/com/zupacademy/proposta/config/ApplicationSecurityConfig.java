package com.zupacademy.proposta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer().jwt();
//				.jwtAuthenticationConverter(getJwtAuthenticationConverter());

		http.headers().frameOptions().disable();

	}

//	JwtAuthenticationConverter getJwtAuthenticationConverter() {
//		JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
//		converter.setAuthoritiesClaimName("authorities");
//		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
//		authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
//		return authenticationConverter;
//	}

	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
