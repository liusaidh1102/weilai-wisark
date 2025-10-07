package com.weilai.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 *
 */
@Configuration
public class SpringConfig {

    //注入发请求的bean
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
