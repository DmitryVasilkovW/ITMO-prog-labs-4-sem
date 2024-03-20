package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSingleton
{
    private static DatabaseSingleton instance;
    private Connection connection;
    private String _url = "jdbc:postgresql://localhost:6432/postgres";
    private String _username = "lab";
    private String _password = "lab1";

    private DatabaseSingleton() throws SQLException
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

    public static DatabaseSingleton getInstance() throws SQLException
    {
        if (instance == null)
        {
            instance = new DatabaseSingleton();
        }
        else if (instance.getConnection().isClosed())
        {
            instance = new DatabaseSingleton();
        }

        return instance;
    }
}
