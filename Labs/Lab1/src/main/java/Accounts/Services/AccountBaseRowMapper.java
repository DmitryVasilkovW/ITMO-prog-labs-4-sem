package Accounts.Services;

import Accounts.Entities.CreditAccount;
import Accounts.Entities.DebitAccount;
import Accounts.Entities.DepositAccount;
import Accounts.Models.AccountBase;
import MyExceptions.IncorrectArgumentException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBaseRowMapper implements RowMapper<AccountBase>
{
    @Override
    public AccountBase mapRow(ResultSet rs, int rowNum) throws SQLException
    {
        if (rs.getString("type").equals(DebitAccount.class.getSimpleName()))
        {
            return new DebitAccount(
                    rs.getInt("id"),
                    rs.getBigDecimal("balance"));
        }
        else if (rs.getString("type").equals(DepositAccount.class.getSimpleName()))
        {
            return new DepositAccount(
                    rs.getInt("id"),
                    rs.getBigDecimal("balance"),
                    rs.getDate("depositEndDate").toLocalDate());
        }
        else if (rs.getString("type").equals(CreditAccount.class.getSimpleName()))
        {
            return new CreditAccount(
                    rs.getInt("id"),
                    rs.getBigDecimal("balance"),
                    rs.getBigDecimal("creditLimit"),
                    rs.getBigDecimal("commission"));
        }

        try
        {
            throw new IncorrectArgumentException();
        } catch (IncorrectArgumentException e)
        {
            throw new RuntimeException(e);
        }
    }
}
