package ru.rndev.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.rndev.integration.annotation.IT;

@IT
@Sql("classpath:sql/data.sql")
@WithMockUser(username = "ruslan@mail.ru", password = "123", authorities = {"ADMIN", "JOUR", "USER"})
public class TestBase {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.4");

    @BeforeAll
    static void runContainer() {
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }

}
