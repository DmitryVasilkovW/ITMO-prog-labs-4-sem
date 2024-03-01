package Task1_4;

import Task1_3.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOOracleMain
{
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String password = "postgres";
        String user = "postgres";
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        ItemDAOOracle dao = new ItemDAOOracle(conn);
        ArrayList<Item> items = dao.selectItems(13, 10);

        System.out.println("Items: " + items);
    }
}
