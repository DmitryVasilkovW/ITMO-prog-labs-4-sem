package Task1_2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest
{
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String pwd = "postgres";
        String name = "postgres";

        try (Connection conn = DriverManager.getConnection(url, name, pwd))
        {
            DatabaseMetaData meta = conn.getMetaData();

            System.out.println("Driver name: " + meta.getDriverName());
            System.out.println("Driver version: " + meta.getDriverVersion());
            System.out.println("Product name: " + meta.getDatabaseProductName());
            System.out.println("Product version: " + meta.getDatabaseProductVersion());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
