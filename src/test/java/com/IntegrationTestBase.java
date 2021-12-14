package com;

import com.configuration.RootConfig;
import com.configuration.SpringConfig;
import com.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Sql("/resources/sql/data.sql")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { SpringConfig.class, RootConfig.class }, initializers = {Postgres.Initializer.class})
@WebAppConfiguration(value = "src/main/com")
@Transactional
public abstract class IntegrationTestBase {

    @BeforeAll
    public static void setUp() {
        Postgres.container.start();
    }
}
