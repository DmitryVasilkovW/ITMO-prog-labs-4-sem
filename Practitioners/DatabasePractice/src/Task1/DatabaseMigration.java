package Task1;

import org.flywaydb.core.Flyway;

public class DatabaseMigration
{
    public static void main(String[] args)
    {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:6432/postgres", "postgres", "postgres")
                .load();

        flyway.migrate();
    }
}
