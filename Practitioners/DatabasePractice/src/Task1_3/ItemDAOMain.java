package Task1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ItemDAOMain
{
    public static void main(String[] args)
    {
        Item item1, item2;

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

        ItemDAOmod dao = new ItemDAOmod();

        item1 = dao.searchById(1);
        item2 = dao.searchById(100);

        System.out.println("Item 1: " + item1);
        System.out.println("Item 2: " + item2);
    }
}
