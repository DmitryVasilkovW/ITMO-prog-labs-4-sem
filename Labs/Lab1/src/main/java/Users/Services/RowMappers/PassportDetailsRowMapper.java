package Users.Services.RowMappers;

import Users.Models.PassportDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassportDetailsRowMapper implements RowMapper<PassportDetails>
{
    @Override
    public PassportDetails mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new PassportDetails(
                rs.getInt("Series"),
                rs.getInt("Number"));
    }
}
