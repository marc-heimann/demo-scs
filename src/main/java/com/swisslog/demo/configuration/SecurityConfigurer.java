package com.swisslog.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/documentation/**")
	        .antMatchers("/**")
	        .antMatchers("/resources/**")
	        .antMatchers("/publics/**")
	        .antMatchers("/asn/**")
	        .antMatchers("/asns/**")
	        .antMatchers("/sku/**")
	        .antMatchers("/skus/**")	   
	    	.antMatchers("/actuator/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/publics/**").hasRole("USER") // no effect
	        .anyRequest().authenticated();
	}
	
}
