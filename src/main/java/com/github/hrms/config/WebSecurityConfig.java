package com.github.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/register/**", "/webjars/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("{scrypt}$e0801$WF5L/Gal2M3DuUr8uHKCCZzSAdSN2t4VcoSzUXHwG+W8aAOHFKbzxRBGhzaKEslDzLOumkwHpXTztWqVcWcpnA==$TjA5jXhcXvMMukcaNjPhPpTM4Veh/lRK4W52Ucjdxeg=")
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password("{scrypt}$e0801$m09hi7tA1FIoYBIP+rIMzYJr0gyDW6ZtuW17wfaOWWD7mcZsjsBwgWV4Q6rJXdKLhsXc/9i7ZhmSPIbWVm9RMA==$IpufFT7llwrK/Z/UhZG6n7LQ7A7YJJMPSlANUTGwh4w=")
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }
}
