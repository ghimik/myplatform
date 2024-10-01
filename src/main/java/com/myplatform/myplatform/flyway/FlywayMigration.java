package com.myplatform.myplatform.flyway;

import org.flywaydb.core.Flyway;

public class FlywayMigration {

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/rution",
                        "postgres", "admin")
                .locations("filesystem:src/main/resources/db/migration")
                .load();

        flyway.migrate();
    }
}
