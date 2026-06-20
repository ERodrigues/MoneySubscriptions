package com.moneysubscriptions.subscriptions.repository;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SqliteMigrationTest {

    @Test
    void shouldApplyMigrationsAgainstSqlite() throws Exception {
        String url = "jdbc:sqlite:file:foundation?mode=memory&cache=shared";
        Flyway flyway = Flyway.configure()
                .dataSource(url, "", "")
                .locations("classpath:db/migration")
                .placeholders(java.util.Map.of("uuid_type", "TEXT", "money_type", "NUMERIC"))
                .load();

        flyway.migrate();

        try (Connection connection = DriverManager.getConnection(url);
             ResultSet resultSet = connection.getMetaData().getTables(null, null, "subscriptions", null)) {
            assertTrue(resultSet.next());
        }
    }
}
