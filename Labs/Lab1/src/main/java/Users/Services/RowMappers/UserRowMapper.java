package Users.Services.RowMappers;

import Users.Entites.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>
{
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new User(
                rs.getString("Name"),
                rs.getString("Surname"),
                null,
                null);
    }
}

