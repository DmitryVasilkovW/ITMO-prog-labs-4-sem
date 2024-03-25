package Database.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLocalHostSingleton
{
    private static DatabaseLocalHostSingleton instance;
    private Connection connection;
    private String _url = "jdbc:postgresql://localhost:6432/postgres";
    private String _username = "lab";
    private String _password = "lab1";

    private DatabaseLocalHostSingleton() throws SQLException
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(_url, _username, _password);
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public static DatabaseLocalHostSingleton getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new DatabaseLocalHostSingleton();
        }
        else if (instance.getConnection().isClosed())
        {
            instance = new DatabaseLocalHostSingleton();
        }

        return instance;
    }
}
