package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieLandApplication {
    private static final Logger logger = LoggerFactory.getLogger(MovieLandApplication.class);

    public static void main(String[] args) {
        logger.info("Application started.");
        SpringApplication.run(MovieLandApplication.class, args);

    }
}
