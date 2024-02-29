package Task1_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCapabilities
{
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String password = "postgres";
        String user = "postgres";

        try
        {
            Class.forName("org.postgresql.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, password))
            {
                var meta = conn.getMetaData();

                System.out.println("Connected to: " + url);
                System.out.println("Connected as: " + user);
                System.out.println("Driver name: " + meta.getDriverName());
                System.out.println("Driver version: " + meta.getDriverVersion());
                System.out.println("Database name: " + meta.getDatabaseProductName());
                System.out.println("Database version: " + meta.getDatabaseProductVersion());
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
}
