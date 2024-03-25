package Database.Repositories;

import Accounts.Models.AccountBase;
import Accounts.Services.AccountBaseRowMapper;
import MyExceptions.ShortageOfFundsException;
import Users.Entites.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;

@Repository
public class AccountRepository
{
    private final NamedParameterJdbcTemplate _jdbcTemplate;

    @Autowired
    public AccountRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        _jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Integer GetUserIdByPassword(String password, String name, String surname)
    {
        String sql = "SELECT Id FROM Users WHERE password = :password and name = :name and surname = :surname";

        var params = new MapSqlParameterSource();
        params.addValue("password", password);
        params.addValue("name", name);
        params.addValue("surname", surname);

        return _jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Transactional
    public void AddAccount(String password, @NotNull User user, String accountType)
    {
        var userId = GetUserIdByPassword(password, user.get_name(), user.get_surname());

        if (userId != null)
        {
            String sql = "INSERT INTO accounts (userid, type) values (:userid, :type)";

            var params = new MapSqlParameterSource();

            params.addValue("userid", userId);
            params.addValue("type", accountType);

            _jdbcTemplate.update(sql, params);
        }
    }

    @Transactional
    public void ReplenishmentOfFunds(BigDecimal amount, Integer accountId)
    {
        String sql = "select balance from accounts where id = :accountId";
        var params = new MapSqlParameterSource();

        params.addValue("accountId", accountId);

        BigDecimal balance = _jdbcTemplate.queryForObject(sql, params, BigDecimal.class);

        balance = balance.add(amount);

        String updateSql = "UPDATE accounts SET balance = :balance WHERE id = :accountId";
        params.addValue("balance", balance);

        _jdbcTemplate.update(updateSql, params);
    }

    @Transactional
    public BigDecimal Withdrawal(BigDecimal amount, Integer accountId) throws ShortageOfFundsException
    {
        String sql = "select balance from accounts where id = :accountId";
        var params = new MapSqlParameterSource();

        params.addValue("accountId", accountId);

        BigDecimal balance = _jdbcTemplate.queryForObject(sql, params, BigDecimal.class);

        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0)
        {
            throw new ShortageOfFundsException();
        }
        else
        {
            balance = balance.subtract(amount);

            String updateSql = "UPDATE accounts SET balance = :balance WHERE id = :accountId";
            params.addValue("balance", balance);

            _jdbcTemplate.update(updateSql, params);
        }

        return amount;
    }

    @Transactional
    public List<AccountBase> GetAccountsByUser(@NotNull User user, String password)
    {
        var userId = GetUserIdByPassword(password, user.get_name(), user.get_surname());
        String sql = "SELECT * FROM accounts WHERE userid = :userId";
        var params = new MapSqlParameterSource();

        params.addValue("userId", userId);

        return _jdbcTemplate.query(sql, params, new AccountBaseRowMapper());
    }

    @Transactional
    public AccountBase GetAccount(@NotNull User user, String password)
    {
        var userId = GetUserIdByPassword(password, user.get_name(), user.get_surname());
        String sql = "SELECT * FROM accounts WHERE userid = :userId";
        var params = new MapSqlParameterSource();
        params.addValue("userId", userId);

        return _jdbcTemplate.queryForObject(sql, params, new AccountBaseRowMapper());
    }

    @Transactional
    public void SaveAccountBalance(@NotNull AccountBase account)
    {
        String sql = "update accounts set balance = :balance where id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("id", account.get_id());
        params.addValue("balance", account.get_balance());

        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public void AddCreditAccount(Integer id, BigDecimal balance, BigDecimal creditLimit, BigDecimal commission)
    {
        String sql = "INSERT INTO accounts (userid, balance, creditLimit, commission, type) VALUES (:id, :balance, :creditLimit, :commission, :type)";
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("balance", balance);
        params.addValue("creditLimit", creditLimit);
        params.addValue("commission", commission);
        params.addValue("type", "CreditAccount");

        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public void AddDebitAccount(Integer id, BigDecimal balance)
    {

        String sql = "INSERT INTO accounts (id, balance, type) VALUES (:id, :balance, :type)";
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("balance", balance);
        params.addValue("type", "DebitAccount");

        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public void AddDepositAccount(Integer id, BigDecimal balance, LocalDate depositEndDate)
    {

        String sql = "INSERT INTO accounts (id, balance, depositEndDate, type) VALUES (:id, :balance, :depositEndDate, :type)";
        var params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("balance", balance);
        params.addValue("depositEndDate", depositEndDate);
        params.addValue("type", "DepositAccount");

        _jdbcTemplate.update(sql, params);
    }

}
