package org.lab3.rowDataMappers;

import org.lab3.dao.CatDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatRowMapper implements RowMapper<CatDao>
{
    @Override
    public CatDao mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var cat = new CatDao();

        cat.setId(rs.getInt("id"));
        cat.setName(rs.getString("name"));
        cat.setBirthDate(rs.getDate("birthdate").toLocalDate());
        cat.setBreed(rs.getString("breed"));
        cat.setColor(rs.getString("color"));
        cat.setOwnerId(rs.getInt("owner_id"));

        return cat;
    }
}


