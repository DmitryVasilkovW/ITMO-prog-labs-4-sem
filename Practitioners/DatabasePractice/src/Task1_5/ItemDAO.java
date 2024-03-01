package Task1_5;

import Task1_3.Item;

import java.sql.*;
import java.math.BigDecimal;

public class ItemDAO
{
    private Connection connection;

    public ItemDAO(Connection connection)
    {
        this.connection = connection;
    }

    public void create(Item item)
    {
        String sql = "INSERT INTO ITEM (TITLE, RELEASEDATE, LISTPRICE, PRICE, VERSION) VALUES (?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, item.getTitle());
            stmt.setDate(2, new java.sql.Date(item.getReleaseDate().getTime()));
            stmt.setBigDecimal(3, new BigDecimal(String.valueOf(item.getPrice())));
            stmt.setBigDecimal(4, new BigDecimal(String.valueOf(item.getPrice())));
            stmt.setInt(5, 1);

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

            connection.commit();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

