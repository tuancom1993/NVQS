package com.nghiavuquansu.configurate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nghiavuquansu.model.PasswordEncoderUtil;

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
		.antMatchers("/css/**").permitAll() 
		.antMatchers("/quanlycongdan/xoacongdan", "/setdatecalculate").hasRole("ADMIN") 
		.antMatchers("/trangchu","/loginsuccess", "/getlistjsonloainghiavu",
					"/quanlycongdan/**").hasAnyRole("ADMIN", "USER")
		.anyRequest().authenticated();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(checkUserLoginService)
			.passwordEncoder(PasswordEncoderUtil.getPasswordEncoder());
	}
	
	
}
