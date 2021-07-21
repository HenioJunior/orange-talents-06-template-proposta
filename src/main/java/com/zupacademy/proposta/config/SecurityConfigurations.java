package com.zupacademy.proposta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/propostas").permitAll()
		.antMatchers(HttpMethod.GET, "/propostas/").permitAll()
		.antMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/health/**").permitAll()
		.and().csrf().disable();
		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
