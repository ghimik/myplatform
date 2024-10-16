package com.myplatform.myplatform.flyway;

import com.myplatform.myplatform.secret.DbPovt;
import org.flywaydb.core.Flyway;

public class FlywayMigration {

    public static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://" + DbPovt.povt + ":5432/rution",
                       DbPovt.username, DbPovt.password)
                .locations("filesystem:src/main/resources/db/migration")
                .load();

        flyway.migrate();
    }
}
