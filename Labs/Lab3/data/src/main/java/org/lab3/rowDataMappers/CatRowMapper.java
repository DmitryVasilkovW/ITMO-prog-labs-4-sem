package org.lab3.rowDataMappers;

import org.lab3.dao.CatDao;
import org.lab3.dao.OwnerDao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CatRowMapper implements RowMapper<CatDao>
{
    @Override
    public CatDao mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        var cat = new CatDao();
        var owner = new OwnerDao();

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


