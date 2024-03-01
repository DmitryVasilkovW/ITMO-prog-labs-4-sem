package Task1_4;

import Task1_3.Item;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOOracle
{
    private Connection connection;

    public ItemDAOOracle(Connection connection)
    {
        this.connection = connection;
    }

    public ArrayList<Item> selectItems(int param1, int param2)
    {
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT 99 id, 'myTitle' title, releasedate reldate from item where 13=? " +
                "UNION ALL " +
                "SELECT 98 id, 'myTitle' title, releasedate reldate from item where 13=?";
        try
        {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, param1);
            pstmt.setInt(2, param2);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Item item = new Item(rs.getInt("id"),
                        rs.getString("title"),
                        "artist",
                        rs.getDate("reldate"),
                        2.39);
                items.add(item);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return items;
    }
}
