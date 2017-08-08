package com.nghiavuquansu.configurate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.nghiavuquansu.common.PasswordEncoderUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfigurate extends WebSecurityConfigurerAdapter {
    @Autowired
    CheckUserLoginService checkUserLoginService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().formLogin().loginPage("/dangnhap").failureUrl("/dangnhap?error=true")
                .usernameParameter("username").passwordParameter("password").successForwardUrl("/loginsuccess").and()
                .authorizeRequests().antMatchers("/dangnhap").permitAll().antMatchers("/css/**").permitAll()
                .antMatchers(getURLMatchersForAdmin()).hasRole("ADMIN")

                // .antMatchers("/trangchu","/loginsuccess",
                // "/getlistjsonloainghiavu",
                // "/quanlycongdan/**", "/thongtincanhan**").hasAnyRole("ADMIN",
                // "USER")
                .anyRequest().authenticated().and().logout().logoutUrl("/thoat").deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/dangnhap");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(checkUserLoginService).passwordEncoder(PasswordEncoderUtils.getPasswordEncoder());
    }

    private String[] getURLMatchersForAdmin() {
        String[] urls = { "/quanlycongdan/xoacongdan", "/setdatecalculate", "/quanlytaikhoan*" };
        return urls;
    }

    private String[] getURLMatchersForUser() {
        String[] urls = {};
        return urls;
    }
}
