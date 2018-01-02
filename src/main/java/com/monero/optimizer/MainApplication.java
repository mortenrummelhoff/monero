package com.monero.optimizer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MainApplication {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public MainApplication() {
        logger.info("Starting MainApplication");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        logger.info("Building restTemplate");
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
