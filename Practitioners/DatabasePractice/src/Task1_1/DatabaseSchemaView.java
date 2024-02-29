package Task1_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseSchemaView
{
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String password = "postgres";
        String user = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT schema_name FROM information_schema.schemata");

            while (rs.next())
            {
                System.out.println(rs.getString("schema_name"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
