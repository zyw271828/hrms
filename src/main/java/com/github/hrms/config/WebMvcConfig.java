package com.github.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public SCryptPasswordEncoder passwordEncoder() {
        SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();
        return sCryptPasswordEncoder;
    }
}
