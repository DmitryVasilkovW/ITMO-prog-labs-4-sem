package Task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Initial
{
    public void GetUpSql()
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement stmt = conn.createStatement();

            stmt.execute("CREATE TABLE Item\n" +
                    "(\n" +
                    "   ITEM_ID     integer GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),\n" +
                    "   Title       VARCHAR(40),\n" +
                    "   Artist      VARCHAR(40),\n" +
                    "   ReleaseDate DATE,\n" +
                    "   ListPrice   numeric(5,2),\n" +
                    "   Price       numeric(5,2),\n" +
                    "   Version     numeric(5),\n" +
                    "   CONSTRAINT  PK_Item  PRIMARY KEY(ITEM_ID)\n" +
                    ");");

            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Diva', 'Annie Lennox', DATE '1992-01-04', 17.97, 13.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Dream of the Blue Turtles', 'Sting', DATE '1985-02-05', 17.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Trouble is...', 'Kenny Wayne Shepherd Band', DATE '1997-08-08', 17.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Lie to Me', 'Jonny Lang', DATE '1997-08-26', 17.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Little Earthquakes', 'Tori Amos', DATE '1992-01-18', 17.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Seal', 'Seal', DATE '1991-08-18', 17.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Ian Moore', 'Ian Moore', DATE '1993-12-05', 9.97, 9.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'So Much for the Afterglow', 'Everclear', DATE '1997-01-19', 16.97, 13.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Surfacing', 'Sarah McLachlan', DATE '1997-12-04', 17.97, 13.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Hysteria', 'Def Leppard', DATE '1987-06-20', 17.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'A Life of Saturdays', 'Dexter Freebish', DATE '2000-12-06', 16.97, 12.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Human Clay', 'Creed', DATE '1999-10-21', 18.97, 13.28,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'My, I''m Large', 'Bobs', DATE '1987-02-20', 11.97, 11.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'So', 'Peter Gabriel', DATE '1986-10-03', 17.97, 13.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Big Ones', 'Aerosmith', DATE '1994-05-08', 18.97, 14.99,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, '90125', 'Yes', DATE '1983-10-16', 11.97, 11.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, '1984', 'Van Halen', DATE '1984-08-19', 11.97, 11.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Escape', 'Journey', DATE '1981-02-25', 11.97, 11.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Greatest Hits', 'Jay and the Americans', DATE '1966-12-05', 13.99, 9.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Ray of Light', 'Madonna', DATE '2000-12-15', 14.99, 10.97,1)");
            stmt.execute("INSERT INTO Item VALUES (DEFAULT, 'Music', 'Madonna', DATE '2002-02-27', 14.99, 11.97,1)");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void GetDownSql()
    {
        String url = "jdbc:postgresql://localhost:6432/postgres";
        String user = "postgres";
        String password = "postgres";

        try (Connection conn = DriverManager.getConnection(url, user, password))
        {
            Statement stmt = conn.createStatement();

            // Удаление таблиц
            stmt.execute("DROP TABLE IF EXISTS users");
            stmt.execute("DROP TABLE IF EXISTS password");
            stmt.execute("DROP TABLE IF EXISTS admin");
            stmt.execute("DROP TABLE IF EXISTS history");
            stmt.execute("DROP SEQUENCE IF EXISTS bill_id_seq");
            stmt.execute("DROP TABLE IF EXISTS bill");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
