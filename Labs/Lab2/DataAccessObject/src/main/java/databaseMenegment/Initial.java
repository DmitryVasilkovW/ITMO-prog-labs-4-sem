package databaseMenegment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Initial
{
    private SessionFactory sessionFactory;

    public Initial()
    {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public void getUpSql() {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();

            String createTablesSQL =
                    "CREATE TABLE owners (" +
                            "Id SERIAL PRIMARY KEY NOT NULL," +
                            " name TEXT NOT NULL," +
                            " birthdate date not null);" +

                            "CREATE TABLE cats (" +
                            "Id SERIAL PRIMARY KEY NOT NULL," +
                            " name TEXT NOT NULL," +
                            " birthdate date not null," +
                            " breed TEXT NOT NULL," +
                            " color TEXT NOT NULL CHECK (color IN ('Black', 'White', 'Brown', 'Gray'))," +
                            " owner_id INT NOT NULL," +
                            " FOREIGN KEY (owner_id) REFERENCES owners(Id));" +

                            "create table owners_cats (" +
                            "cat_id int not null," +
                            "owner_id int not null," +
                            "foreign key (owner_id) references owners(Id)," +
                            "foreign key (cat_id) references cats(id));" +

                            "CREATE TABLE cats_friends (" +
                            "cat_id INT NOT NULL," +
                            " friend_id INT NOT NULL," +
                            " PRIMARY KEY (cat_id, friend_id)," +
                            " FOREIGN KEY (cat_id) REFERENCES cats(Id)," +
                            " FOREIGN KEY (friend_id) REFERENCES cats(Id));";

            session.createNativeQuery(createTablesSQL).executeUpdate();

            transaction.commit();
        }
    }

    public void getDownSql()
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();

            String dropTablesSQL =
                    "drop table if exists cats_owners;" +
                            "drop table if exists cats_friends;" +
                            "drop table if exists cats;" +
                            "drop table if exists owners;";

            session.createNativeQuery(dropTablesSQL).executeUpdate();

            transaction.commit();
        }
    }
}