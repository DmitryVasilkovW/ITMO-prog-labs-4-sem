package org.lab3.rowDataMappers;

import org.lab3.models.Owner;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRowMapper implements RowMapper<Owner>
{
    @Override
    public Owner mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var owner = new Owner();

        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        owner.setBirthDate(rs.getDate("birthdate").toLocalDate());

        return owner;
    }
}
