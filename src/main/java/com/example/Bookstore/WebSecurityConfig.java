package com.example.Bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Bookstore.web.UserDetailServiceImpl;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
    
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeHttpRequests(authorize -> authorize
			.requestMatchers(antMatcher("/css/**")).permitAll()
			.requestMatchers(antMatcher("/books/**")).permitAll()
				.anyRequest().authenticated()
		)
		.formLogin(formLogin -> formLogin
			//.loginPage("/login")
			.defaultSuccessUrl("/booklist", true).permitAll()
		)
		.logout(logout -> logout
				.permitAll()
		)
		.csrf(csrf -> csrf
				.disable()
		);
		return http.build();
	}
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}}