package org.lab3.rowDataMappers;

import org.lab3.models.Cat;
import org.lab3.models.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatRowMapper implements RowMapper<Cat>
{
    @Override
    public Cat mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var cat = new Cat();
        var owner = new Owner();

        cat.setId(rs.getInt("cat_id"));
        cat.setName(rs.getString("cat_name"));
        cat.setBirthDate(rs.getDate("cat_birthdate").toLocalDate());
        cat.setBreed(rs.getString("cat_breed"));
        cat.setColor(rs.getString("cat_color"));

        owner.setId(rs.getInt("owner_id"));
        owner.setName(rs.getString("owner_name"));
        owner.setBirthDate(rs.getDate("owner_birthdate").toLocalDate());

        cat.setOwner(owner);

        return cat;
    }
}


