package org.lab3.rowDataMappers;

import org.lab3.dao.OwnerDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerRowMapper implements RowMapper<OwnerDao>
{
    @Override
    public OwnerDao mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var owner = new OwnerDao();

        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        owner.setBirthDate(rs.getDate("birthdate").toLocalDate());

        return owner;
    }
}
