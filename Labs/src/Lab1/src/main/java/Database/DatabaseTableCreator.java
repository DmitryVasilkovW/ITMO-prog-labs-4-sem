package Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableCreator
{
    private DatabaseSingleton _databaseSingleton;

    public DatabaseTableCreator()
    {
        try
        {
            this._databaseSingleton = DatabaseSingleton.getInstance();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void GetUpSql()
    {
        String createTablesSQL =
                "CREATE TABLE "
                + "users" + "("
                + "Id INT PRIMARY KEY NOT NULL,"
                + "Name TEXT NOT NULL);"

                + "CREATE TABLE "
                + "address" + "("
                + "Id INT PRIMARY KEY NOT NULL,"
                + "UserId INT,"
                + "FOREIGN KEY (UserId) REFERENCES users(Id),"
                + "Street TEXT NOT NULL,"
                + "House TEXT NOT NULL,"
                + "Flore INT NOT NULL,"
                + "NumberOfApartment INT NOT NULL);"

                + "CREATE TABLE "
                + "PassportDetails" + "("
                + "Id INT PRIMARY KEY NOT NULL,"
                + "UserId INT,"
                + "FOREIGN KEY (UserId) REFERENCES users(Id),"
                + "Series INT NOT NULL ,"
                + "Number INT NOT NULL);";

        try
        {
            Connection connection = this._databaseSingleton.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(createTablesSQL);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}