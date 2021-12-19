package com;

import com.initializer.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Sql("test/resources/sql/movie.sql")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = {Postgres.Initializer.class})
@WebAppConfiguration(value = "src/main/com")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

    @BeforeAll
    public static void setUp() {
        Postgres.container.start();
    }
}
