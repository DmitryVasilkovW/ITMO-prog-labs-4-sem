package Task1_1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ItemTableStructureEvaluation
{
    public static void main(String[] args)
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String password = "postgres";
        String user = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement stmt = conn.createStatement();


            ResultSet rs = stmt.executeQuery(
                    "SELECT column_name, data_type, character_maximum_length " +
                            "FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'item'"
            );

            while (rs.next())
            {
                System.out.println("Column: " + rs.getString("column_name"));
                System.out.println("Type: " + rs.getString("data_type"));
                System.out.println("Max length: " + rs.getString("character_maximum_length"));
                System.out.println();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
