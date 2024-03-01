package Task1_4;

import Task1_3.Item;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDaoMain
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

        ItemDAO dao = new ItemDAO(conn);

        ArrayList<Item> itemsOf = dao.searchByKeyword("of");
        ArrayList<Item> itemsGay = dao.searchByKeyword("Gay");

        System.out.println("Number of items with 'of' in the title: " + itemsOf.size());
        System.out.println("Number of items with 'Gay' in the title: " + itemsGay.size());

        System.out.println("Items with 'of' in the title: " + itemsOf);
        System.out.println("Items with 'Gay' in the title: " + itemsGay);
    }
}

