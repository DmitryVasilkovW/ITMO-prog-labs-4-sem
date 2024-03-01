package Task1_5;

import Task1_3.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
            conn.setAutoCommit(false);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }

        var dao = new ItemDAO(conn);
        var searchdao = new Task1_4.ItemDAO(conn);

        var newItem = new Item(0, "New Title", "tmp" , new Date(), new BigDecimal("239"), 1);

        dao.create(newItem);

        ArrayList<Item> items = searchdao.searchByKeyword("New Title");
        System.out.println("Items with 'New Title' in the title: " + items);
    }
}
