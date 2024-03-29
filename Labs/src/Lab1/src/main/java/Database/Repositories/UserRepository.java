package Database.Repositories;

import Users.Entites.User;
import Users.Models.Address;
import Users.Models.PassportDetails;
import Users.Services.RowMappers.AddressRowMapper;
import Users.Services.RowMappers.PassportDetailsRowMapper;
import Users.Services.RowMappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Repository
public class UserRepository
{
    private final NamedParameterJdbcTemplate _jdbcTemplate;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        _jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public Integer GetUserIdByPassword(String password)
    {
        String sql = "SELECT Id FROM Users WHERE password = :password";

        var params = new MapSqlParameterSource();

        params.addValue("password", password);

        return _jdbcTemplate.queryForObject(sql, params, Integer.class);
    }

    @Transactional
    public boolean CheckPassword(String name, String surname, String password)
    {
        String sqlForPassword = "select password from users where name = :name and surname = :surname and password = :password";

        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("surname", surname);
        params.addValue("password", password);

        List<String> passwords = _jdbcTemplate.query(sqlForPassword, params, (rs, rowNum) -> rs.getString("password"));

        return !passwords.isEmpty();
    }

    @Transactional
    public boolean CheckIfUserCanWithdraw(String name, String surname, String password, Integer accountId, BigDecimal amount)
    {
        String sql = "SELECT balance FROM accounts WHERE id = :accountId AND UserId = (SELECT Id FROM users WHERE name = :name AND surname = :surname AND password = :password)";

        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("surname", surname);
        params.addValue("password", password);
        params.addValue("accountId", accountId);

        try
        {
            BigDecimal balance = _jdbcTemplate.queryForObject(sql, params, BigDecimal.class);

            if (balance != null && balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0)
            {
                return true;
            }
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }

        return false;
    }

    @Transactional
    public boolean CheckIfUserCanUseThisAccount(String name, String surname, String password, Integer accountId)
    {
        String sql = "SELECT balance FROM accounts WHERE id = :accountId AND UserId = (SELECT Id FROM users WHERE name = :name AND surname = :surname AND password = :password)";

        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("surname", surname);
        params.addValue("password", password);
        params.addValue("accountId", accountId);

        try
        {
            BigDecimal balance = _jdbcTemplate.queryForObject(sql, params, BigDecimal.class);

            if (balance != null)
            {
                return true;
            }
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }

        return false;
    }



    @Transactional
    public void AddUser(String name, String surname, String password)
    {
        String sql = "INSERT INTO Users (name, surname, password) VALUES (:name, :surname, :password)";

        var params = new MapSqlParameterSource();

        params.addValue("name", name);
        params.addValue("surname", surname);
        params.addValue("password", password);

        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public void AddAddress(
            String street,
            String house,
            int flore,
            int numberOfApartment,
            String password)
    {
        var userId = GetUserIdByPassword(password);

        if (userId != null)
        {
            var sqlForAddress = "INSERT INTO address (UserId, Street, House, Flore, NumberOfApartment)" +
                    " VALUES (:userId, :street, :house, :flore, :numberOfApartment)";

            var paramsForAddress = new MapSqlParameterSource();

            paramsForAddress.addValue("userId", userId);
            paramsForAddress.addValue("street", street);
            paramsForAddress.addValue("house", house);
            paramsForAddress.addValue("flore", flore);
            paramsForAddress.addValue("numberOfApartment", numberOfApartment);

            _jdbcTemplate.update(sqlForAddress, paramsForAddress);
        }
        else
        {
            throw new RuntimeException("User with given password does not exist");
        }
    }

    @Transactional
    public void AddPassportDetails(int series, int number, String password)
    {
        var userId = GetUserIdByPassword(password);

        if (userId != null)
        {
            var sqlForPassportDetails = "INSERT INTO passportdetails (userid, series, number)" +
                    "VALUES (:userId, :series, :number)";

            var paramsForPassportDetails = new MapSqlParameterSource();

            paramsForPassportDetails.addValue("userId", userId);
            paramsForPassportDetails.addValue("series", series);
            paramsForPassportDetails.addValue("number", number);

            _jdbcTemplate.update(sqlForPassportDetails, paramsForPassportDetails);
        }
        else
        {
            throw new RuntimeException("User with given password does not exist");
        }
    }

    @Transactional
    public User GetUserByPassword(String password)
    {
        var userId = GetUserIdByPassword(password);

        if (userId != null)
        {
            var sqlForUser = "SELECT * FROM users WHERE Id = :userId";
            var sqlForAddress = "SELECT * FROM address WHERE UserId = :userId";
            var sqlForPassportDetails = "SELECT * FROM PassportDetails WHERE UserId = :userId";

            var params = new MapSqlParameterSource();

            params.addValue("userId", userId);

            User user = _jdbcTemplate.queryForObject(sqlForUser, params, new UserRowMapper());
            Address address = _jdbcTemplate.queryForObject(sqlForAddress, params, new AddressRowMapper());
            PassportDetails passportDetails = _jdbcTemplate.queryForObject(sqlForPassportDetails, params, new PassportDetailsRowMapper());

            assert user != null;
            user.InitAddress(address);
            user.InitPassportDetails(passportDetails);

            return user;
        }

        throw new RuntimeException("User with given password does not exist");
    }

}