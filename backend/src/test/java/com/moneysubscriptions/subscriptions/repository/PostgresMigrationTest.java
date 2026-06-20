package com.moneysubscriptions.subscriptions.repository;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class PostgresMigrationTest {

    @Container
    static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:18.4");

    @Test
    void shouldApplyMigrationsAgainstPostgres() throws Exception {
        Flyway flyway = Flyway.configure()
                .dataSource(POSTGRES.getJdbcUrl(), POSTGRES.getUsername(), POSTGRES.getPassword())
                .locations("classpath:db/migration")
                .placeholders(java.util.Map.of("uuid_type", "UUID", "money_type", "NUMERIC(12,2)"))
                .load();

        flyway.migrate();

        try (Connection connection = DriverManager.getConnection(
                POSTGRES.getJdbcUrl(),
                POSTGRES.getUsername(),
                POSTGRES.getPassword()
        ); ResultSet resultSet = connection.getMetaData().getTables(null, null, "subscription_types", null)) {
            assertTrue(resultSet.next());
        }
    }
}
