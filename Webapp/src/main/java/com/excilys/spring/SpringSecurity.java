package com.excilys.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers(HttpMethod.GET,"/","/dashboard").hasAnyRole("user","admin")
		.antMatchers(HttpMethod.POST,"/","/dashboard").hasRole("admin")
		.antMatchers("/addComputer").hasRole("admin")
		.antMatchers("/editComputer").hasRole("admin")
		.and()
		.formLogin()
		.and()
		.logout()
		.and().csrf().disable();
	}
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}