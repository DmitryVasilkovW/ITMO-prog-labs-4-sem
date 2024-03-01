package Task1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ItemDAO
{
    private Connection conn;

    public ItemDAO(String url, String user, String password)
    {
        try
        {
            Class.forName("org.postgresql.Driver");

            this.conn = DriverManager.getConnection(url, user, password);
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet searchById(int id)
    {
        try
        {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM item WHERE item_id = ?");
            stmt.setInt(1, id);
            return stmt.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public void close()
    {
        try
        {
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
