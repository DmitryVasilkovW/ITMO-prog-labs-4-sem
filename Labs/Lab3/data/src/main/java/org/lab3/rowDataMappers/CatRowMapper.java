package org.lab3.rowDataMappers;

import org.lab3.models.Cat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatRowMapper implements RowMapper<Cat>
{

    @Override
    public Cat mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        Cat cat = new Cat();
        cat.setId(rs.getInt("id"));
        cat.setName(rs.getString("name"));
        cat.setBirthDate(rs.getDate("birthdate").toLocalDate());
        cat.setBreed(rs.getString("breed"));
        cat.setColor(rs.getString("color"));

        return cat;
    }
}
