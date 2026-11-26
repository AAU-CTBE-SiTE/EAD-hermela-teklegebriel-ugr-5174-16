package com.craft.customer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class CustomerConfig {

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl("http://localhost:9090")
                .build();
    }
}
