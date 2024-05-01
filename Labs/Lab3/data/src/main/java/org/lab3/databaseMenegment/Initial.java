package org.lab3.databaseMenegment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Initial 
{

    private final NamedParameterJdbcTemplate _jdbcTemplate;

    @Autowired
    public Initial(NamedParameterJdbcTemplate jdbcTemplate)
    {
        _jdbcTemplate = jdbcTemplate;
    }

    public void getUpSql()
    {
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

        _jdbcTemplate.getJdbcOperations().execute(createTablesSQL);
    }

    public void getDownSql()
    {
        String dropTablesSQL =
                "drop table if exists owners_cats;" +
                        "drop table if exists cats_friends;" +
                        "drop table if exists cats;" +
                        "drop table if exists owners;";

        _jdbcTemplate.getJdbcOperations().execute(dropTablesSQL);
    }
}
