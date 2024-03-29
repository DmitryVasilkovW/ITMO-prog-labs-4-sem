package Database.Repositories;

import Bank.Entities.Bank;
import Bank.Services.RowMappers.BankRowMapper;
import Users.Entites.User;
import Users.Models.Address;
import Users.Models.PassportDetails;
import Users.Services.RowMappers.AddressRowMapper;
import Users.Services.RowMappers.PassportDetailsRowMapper;
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
    public List<Bank> GetAllBanks(String password)
    {
        String sql = "SELECT * FROM banks";
        List<Bank> banks = jdbcTemplate.query(sql, new BankRowMapper());

        for (Bank bank : banks)
        {
            Map<String, User> users = GetUsersForBank(bank.get_id());
            var userToAccountRatio = new HashMap<String, List<Integer>>();

            for (User user : users.values())
            {
                if (CheckPassword(user.get_name(), user.get_surname(), password))
                {
                    HashMap<String, List<Integer>> tmpRatio = GetUserToAccountRatio(user.get_name(), user.get_surname(), password, bank.get_id());
                    userToAccountRatio.put(user.get_name() + user.get_surname() + password, tmpRatio.get(user.get_name() + user.get_surname() + password));
                }
            }

            bank.ActivateBank((HashMap<String, User>) users, userToAccountRatio);
        }

        return banks;
    }

    @Transactional
    public List<Bank> GetAllBanks(List<String> passwords)
    {
        String sql = "SELECT * FROM banks";
        List<Bank> banks = jdbcTemplate.query(sql, new BankRowMapper());

        for (Bank bank : banks)
        {
            Map<String, User> users = GetUsersForBank(bank.get_id());
            var userToAccountRatio = new HashMap<String, List<Integer>>();

            assert (passwords.size() == users.size()) != false;
            for (int i = 0; i < passwords.size(); i++)
            {
                if (CheckPassword(users.get(i).get_name(), users.get(i).get_surname(), passwords.get(i)))
                {
                    HashMap<String, List<Integer>> tmpRatio = GetUserToAccountRatio(users.get(i).get_name(), users.get(i).get_surname(), passwords.get(i), bank.get_id());
                    userToAccountRatio.put(
                            users.get(i).get_name() + users.get(i).get_surname() + passwords.get(i),
                            tmpRatio.get(users.get(i).get_name() + users.get(i).get_surname() + passwords.get(i)));
                }
            }

            bank.ActivateBank((HashMap<String, User>) users, userToAccountRatio);
        }

        return banks;
    }

    @Transactional
    public HashMap<String, List<Integer>> GetUserToAccountRatio(String name, String surname, String password, Integer bankId)
    {
        if (CheckPassword(name, surname, password))
        {
            String sqlForUserId = "SELECT id FROM users WHERE name = :name AND surname = :surname AND password = :password";
            String sqlForAccountIds = "SELECT id FROM accounts WHERE UserId = :userId AND bankid = :bankId";

            var params = new MapSqlParameterSource();
            params.addValue("name", name);
            params.addValue("surname", surname);
            params.addValue("password", password);
            params.addValue("bankId", bankId);

            Integer userId = jdbcTemplate.queryForObject(sqlForUserId, params, Integer.class);

            params.addValue("userId", userId);

            List<Integer> accounts = jdbcTemplate.query(sqlForAccountIds, params, (rs, rowNum) -> rs.getInt("id"));

            var userToAccountRatio = new HashMap<String, List<Integer>>();
            userToAccountRatio.put(name + surname + password, accounts);

            return userToAccountRatio;
        }

        return null;
    }


    @Transactional
    public boolean CheckPassword(String name, String surname, String password)
    {
        String sqlForPassword = "select password from users where name = :name and surname = :surname and password = :password";

        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("surname", surname);
        params.addValue("password", password);

        List<String> passwords = jdbcTemplate.query(sqlForPassword, params, (rs, rowNum) -> rs.getString("password"));

        return !passwords.isEmpty();
    }


    @Transactional
    public Map<String, User> GetUsersForBank(int bankId)
    {
        String sqlForUserId = "SELECT userid FROM bank_clients WHERE BankId = :bankId";
        String sql = "SELECT * FROM users WHERE Id IN (SELECT UserId FROM bank_clients WHERE BankId = :bankId)";
        String sqlForPasswords = "SELECT password FROM users WHERE Id IN (SELECT UserId FROM bank_clients WHERE BankId = :bankId)";
        String sqlForAddress = "SELECT * FROM address WHERE UserId = :userId";
        String sqlForPassportDetails = "SELECT * FROM passportdetails WHERE UserId = :userId";

        var params = new MapSqlParameterSource();
        params.addValue("bankId", bankId);

        List<Integer> userIds = jdbcTemplate.query(sqlForUserId, params, (rs, rowNum) -> rs.getInt("userid"));

        List<User> users = jdbcTemplate.query(sql, params, new UserRowMapper());
        List<String> passwords = jdbcTemplate.query(sqlForPasswords, params, (rs, rowNum) -> rs.getString("password"));

        Map<String, User> userMap = new HashMap<>();

        for (int i = 0; i < users.size(); i++)
        {
            User user = users.get(i);
            Integer userId = userIds.get(i);

            params.addValue("userId", userId);

            List<Address> addresses = jdbcTemplate.query(sqlForAddress, params, new AddressRowMapper());
            List<PassportDetails> passportDetails = jdbcTemplate.query(sqlForPassportDetails, params, new PassportDetailsRowMapper());

            user.InitAddress((addresses.isEmpty() ? null : addresses.get(0)));
            user.InitPassportDetails(passportDetails.isEmpty() ? null : passportDetails.get(0));

            userMap.put(passwords.get(i), user);
        }

        return userMap;
    }


}
