package Bank.Services.RowMappers;

import Bank.Entities.Bank;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankRowMapper implements RowMapper<Bank>
{
    @Override
    public Bank mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        return new Bank(
                rs.getInt("id"),
                rs.getString("Name"),
                rs.getBigDecimal("ReserveFund"),
                rs.getBigDecimal("Commission"),
                null
        );
    }
}
