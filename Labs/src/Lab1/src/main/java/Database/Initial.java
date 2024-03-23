package Database;

import Database.Connection.DatabaseLocalHostSingleton;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Initial
{
    private DatabaseLocalHostSingleton _databaseSingleton;

    public Initial()
    {
        try
        {
            _databaseSingleton = DatabaseLocalHostSingleton.getInstance();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Transactional
    public void GetUpSql()
    {
        String createTablesSQL =
                "CREATE TABLE "
                + "users" + "("
                + "Id SERIAL PRIMARY KEY NOT NULL,"
                + "Name TEXT NOT NULL,"
                + "Surname TEXT NOT NULL,"
                + "Password INT NOT NULL);"

                + "CREATE TABLE "
                + "address" + "("
                + "Id SERIAL PRIMARY KEY NOT NULL,"
                + "UserId INT,"
                + "FOREIGN KEY (UserId) REFERENCES users(Id),"
                + "Street TEXT NOT NULL,"
                + "House TEXT NOT NULL,"
                + "Flore INT NOT NULL,"
                + "NumberOfApartment INT NOT NULL);"

                + "CREATE TABLE "
                + "PassportDetails" + "("
                + "Id SERIAL PRIMARY KEY NOT NULL,"
                + "UserId INT,"
                + "FOREIGN KEY (UserId) REFERENCES users(Id),"
                + "Series INT NOT NULL ,"
                + "Number INT NOT NULL);"

                + "CREATE TABLE "
                + "accounts" + "("
                + "id SERIAL PRIMARY KEY NOT NULL,"
                + "UserId INT,"
                + "Type TEXT NOT NULL,"
                + "creditLimit NUMERIC, "
                + "commission NUMERIC,"
                + "interestRate NUMERIC,"
                + "depositEndDate DATE,"
                + "FOREIGN KEY (UserId) REFERENCES users(Id),"
                + "balance NUMERIC NOT NULL DEFAULT 0);";

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

    @Transactional
    public void GetDownSql()
    {
        String dropTablesSQL =
                "drop table if exists accounts;" +
                "drop table if exists address;" +
                "drop table if exists PassportDetails;" +
                "drop table if exists users";

        try
        {
            Connection connection = this._databaseSingleton.getConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(dropTablesSQL);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}