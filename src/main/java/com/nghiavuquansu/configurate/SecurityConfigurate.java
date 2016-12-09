package com.nghiavuquansu.configurate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurate extends WebSecurityConfigurerAdapter {
	@Autowired CheckUserLoginService checkUserLoginService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.formLogin().loginPage("/dangnhap").failureUrl("/dangnhap?error=true")
		.usernameParameter("username").passwordParameter("password")
		.successForwardUrl("/loginsuccess")
		.and()
		.authorizeRequests()
		.antMatchers("/dangnhap").permitAll() 
		.antMatchers("/home","/loginsuccess", "/themcongdan", "/getlistjsonloainghiavu").hasRole("ADMIN");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(checkUserLoginService);
	}
}
