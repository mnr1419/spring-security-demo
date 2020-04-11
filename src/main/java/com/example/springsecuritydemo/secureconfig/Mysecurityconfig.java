package com.example.springsecuritydemo.secureconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class Mysecurityconfig extends WebSecurityConfigurerAdapter {
	 
	@Override
    public void configure(WebSecurity web) throws Exception {
        
            web.ignoring().antMatchers("/rest/auth/getInfo");
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("myadmin").password("{noop}password1").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("myuser").password("{noop}password2").roles("USER");
    }
 
    // Security based on role.
    // Here the user role will be shown the Http 403 - Access Denied Error. But the admin role will be shown the successful page.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/rest/auth/getmsg").hasAnyRole("ADMIN").anyRequest().fullyAuthenticated().and().httpBasic();
       /* http.authorizeRequests() 
        .antMatchers("/register").denyAll();*/
    }
 
   
}
