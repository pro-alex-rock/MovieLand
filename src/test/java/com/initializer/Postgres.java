package com.initializer;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class Postgres {

    public static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.1");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "postgres.url=" + container.getJdbcUrl(),
                    "postgres.user=" + container.getUsername(),
                    "postgres.password=" + container.getPassword()
            ).applyTo(applicationContext);
        }
    }
}