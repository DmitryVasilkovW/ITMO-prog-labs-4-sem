package Database.Repositories;

import Bank.Entities.Bank;
import Bank.Services.RowMappers.BankRowMapper;
import Users.Entites.User;
import Users.Services.RowMappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository
{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public BankRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Bank> GetAllBanks()
    {
        String sql = "SELECT * FROM banks";
        List<Bank> banks = jdbcTemplate.query(sql, new BankRowMapper());

        for (Bank bank : banks)
        {
            Map<String, User> users = getUsersForBank(bank.get_id());

            bank.ActivateBank((HashMap<String, User>) users);
        }

        return banks;
    }

    @Transactional
    public Map<String, User> getUsersForBank(int bankId)
    {
        String sql = "SELECT * FROM users WHERE BankId = :bankId";
        String sqlForPasswords = "SELECT password FROM users WHERE BankId = :bankId";
        var params = new MapSqlParameterSource();

        params.addValue("bankId", bankId);

        List<User> users = jdbcTemplate.query(sql, params, new UserRowMapper());
        List<String> passwords = jdbcTemplate.query(sqlForPasswords, params, (rs, rowNum) -> rs.getString("password"));

        Map<String, User> userMap = new HashMap<>();

        for (int i = 0; i < users.size(); i++)
        {
            userMap.put(passwords.get(i), users.get(i));
        }

        return userMap;
    }
}
