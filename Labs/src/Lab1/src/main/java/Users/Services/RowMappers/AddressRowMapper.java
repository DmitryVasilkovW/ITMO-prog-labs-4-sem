package Users.Services.RowMappers;

import Users.Models.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address>
{
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Address(
                rs.getString("Street"),
                rs.getString("House"),
                rs.getInt("Flore"),
                rs.getInt("NumberOfApartment"));
    }
}
