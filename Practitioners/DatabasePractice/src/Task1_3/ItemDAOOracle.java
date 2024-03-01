package Task1_3;

import java.sql.*;

public class ItemDAOOracle
{
    private Connection connection;

    public ItemDAOOracle(Connection connection)
    {
        this.connection = connection;
    }

    public Item selectItem(int id)
    {
        Item item = null;

        try
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT 99 id, 'myTitle' title, releasedate reldate from item where 1=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                item = new Item(rs.getInt("item_id"), rs.getString("title"), "239", rs.getDate("reldate"), 2.0);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return item;
    }
}
