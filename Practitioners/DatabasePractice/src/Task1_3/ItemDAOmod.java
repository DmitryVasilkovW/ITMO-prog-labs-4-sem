package Task1_3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class ItemDAOmod
{
    public Item searchById(int id)
    {
        Item result = null;
        PreparedStatement stmt = null;
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");

            String url = "jdbc:postgresql://localhost:6432/postgres";
            String password = "postgres";
            String user = "postgres";

            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT * FROM Item WHERE item_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                result = new Item();
                result.setId((int) rs.getLong("item_id"));
                result.setTitle(rs.getString("title"));
                result.setArtist(rs.getString("artist"));
                result.setReleaseDate(rs.getDate("releasedate"));
                result.setPrice(rs.getBigDecimal("listprice"));
                result.setVersion(rs.getInt("version"));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if (conn != null)
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
        return result;
    }
}
