package Task1_3;


import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

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
        catch (SQLException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        ItemDAOOracle dao = new ItemDAOOracle(conn);
        Item item = dao.selectItem(1);

        System.out.println("Item: " + item);
    }
}

