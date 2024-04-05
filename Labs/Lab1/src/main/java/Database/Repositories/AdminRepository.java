package Database.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class AdminRepository
{
    private final NamedParameterJdbcTemplate _jdbcTemplate;

    @Autowired
    public AdminRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        _jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void AddPassportDetails(int series, int number, Integer userId) throws IllegalArgumentException
    {
        String checkSql = "SELECT COUNT(*) FROM passportdetails WHERE userid = :userId";
        var checkParams = new MapSqlParameterSource();
        checkParams.addValue("userId", userId);
        Integer count = _jdbcTemplate.queryForObject(checkSql, checkParams, Integer.class);

        if (count != null && count > 0)
        {
            throw new IllegalArgumentException("Passport data for this user already exists");
        }

        var sqlForPassportDetails = "INSERT INTO passportdetails (userid, series, number)" +
                "VALUES (:userId, :series, :number)";

        var paramsForPassportDetails = new MapSqlParameterSource();
        paramsForPassportDetails.addValue("userId", userId);
        paramsForPassportDetails.addValue("series", series);
        paramsForPassportDetails.addValue("number", number);

        _jdbcTemplate.update(sqlForPassportDetails, paramsForPassportDetails);
    }

    @Transactional
    public void UpdatePassportDetails(int series, int number, Integer userId)
    {
        var sqlForPassportDetails = "UPDATE passportdetails SET series = :series, number = :number WHERE userid = :userId";

        var paramsForPassportDetails = new MapSqlParameterSource();
        paramsForPassportDetails.addValue("userId", userId);
        paramsForPassportDetails.addValue("series", series);
        paramsForPassportDetails.addValue("number", number);

        _jdbcTemplate.update(sqlForPassportDetails, paramsForPassportDetails);
    }

    @Transactional
    public void AddAddress(
            String street,
            String house,
            int flore,
            int numberOfApartment,
            Integer userId)
    {
        String checkSql = "SELECT COUNT(*) FROM address WHERE UserId = :userId";
        var checkParams = new MapSqlParameterSource();
        checkParams.addValue("userId", userId);
        Integer count = _jdbcTemplate.queryForObject(checkSql, checkParams, Integer.class);

        if (count != null && count > 0)
        {
            throw new IllegalArgumentException("The address for this user already exists");
        }

        var sqlForAddress = "INSERT INTO address (UserId, Street, House, Flore, NumberOfApartment)" +
                "VALUES (:userid, :street, :house, :flore, :numberOfApartment)";

        var paramsForAddress = new MapSqlParameterSource();
        paramsForAddress.addValue("userid", userId);
        paramsForAddress.addValue("street", street);
        paramsForAddress.addValue("house", house);
        paramsForAddress.addValue("flore", flore);
        paramsForAddress.addValue("numberOfApartment", numberOfApartment);

        _jdbcTemplate.update(sqlForAddress, paramsForAddress);
    }

    @Transactional
    public void UpdateAddress(
            String street,
            String house,
            int flore,
            int numberOfApartment,
            Integer userId)
    {
        var sqlForAddress = "UPDATE address SET Street = :street, House = :house, Flore = :flore, NumberOfApartment = :numberOfApartment WHERE UserId = :userid";

        var paramsForAddress = new MapSqlParameterSource();
        paramsForAddress.addValue("userid", userId);
        paramsForAddress.addValue("street", street);
        paramsForAddress.addValue("house", house);
        paramsForAddress.addValue("flore", flore);
        paramsForAddress.addValue("numberOfApartment", numberOfApartment);

        _jdbcTemplate.update(sqlForAddress, paramsForAddress);
    }

    @Transactional
    public void AddBank(String name, BigDecimal reserveFund, BigDecimal commission)
    {
        String checkSql = "SELECT COUNT(*) FROM banks WHERE name = :name";
        var checkParams = new MapSqlParameterSource();
        checkParams.addValue("name", name);
        Integer count = _jdbcTemplate.queryForObject(checkSql, checkParams, Integer.class);

        if (count != null && count > 0)
        {
            throw new IllegalArgumentException("A bank with this name already exists");
        }

        String sql = "INSERT INTO banks (name, reservefund, commission) VALUES (:name, :reserveFund, :commission)";
        var params = new MapSqlParameterSource();

        params.addValue("name", name);
        params.addValue("reserveFund", reserveFund);
        params.addValue("commission", commission);

        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public boolean CheckAdmin(String name, String password)
    {
        String sql = "select name from admins where name = :name and password = :password";
        var params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("password", password);

        List<String> result = _jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getString("name"));

        if (result.isEmpty())
        {
            return false;
        }

        return true;
    }

    @Transactional
    public void AddUser(String name, String surname, String password)
    {
        String checkSql = "SELECT COUNT(*) FROM Users WHERE name = :name AND surname = :surname AND password = :password";
        var checkParams = new MapSqlParameterSource();
        checkParams.addValue("name", name);
        checkParams.addValue("surname", surname);
        checkParams.addValue("password", password);
        Integer count = _jdbcTemplate.queryForObject(checkSql, checkParams, Integer.class);

        if (count != null && count > 0)
        {
            throw new IllegalArgumentException("incorrect  user information");
        }

        String sql = "INSERT INTO Users (name, surname, password) VALUES (:name, :surname, :password)";
        var params = new MapSqlParameterSource();

        params.addValue("name", name);
        params.addValue("surname", surname);
        params.addValue("password", password);

        _jdbcTemplate.update(sql, params);
    }


    @Transactional
    public void AddCreditAccount(Integer id, BigDecimal balance, BigDecimal creditLimit, BigDecimal commission, String bankName)
    {
        String sqlForBankId = "SELECT Id FROM banks WHERE Name = :bankName";
        var paramsForBankId = new MapSqlParameterSource();
        paramsForBankId.addValue("bankName", bankName);
        Integer bankId = _jdbcTemplate.queryForObject(sqlForBankId, paramsForBankId, Integer.class);

        String sqlForBankClients = "INSERT INTO bank_clients (UserId, BankId) VALUES (:userId, :bankId)";
        var paramsForBankClients = new MapSqlParameterSource();
        paramsForBankClients.addValue("userId", id);
        paramsForBankClients.addValue("bankId", bankId);
        _jdbcTemplate.update(sqlForBankClients, paramsForBankClients);

        String sql = "INSERT INTO accounts (UserId, bankid, balance, creditLimit, commission, type) VALUES (:userId, :bankId, :balance, :creditLimit, :commission, :type)";
        var params = new MapSqlParameterSource();
        params.addValue("userId", id);
        params.addValue("bankId", bankId);
        params.addValue("balance", balance);
        params.addValue("creditLimit", creditLimit);
        params.addValue("commission", commission);
        params.addValue("type", "CreditAccount");
        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public void AddDebitAccount(Integer id, BigDecimal balance, String bankName)
    {
        String sqlForBankId = "SELECT Id FROM banks WHERE Name = :bankName";
        var paramsForBankId = new MapSqlParameterSource();
        paramsForBankId.addValue("bankName", bankName);
        Integer bankId = _jdbcTemplate.queryForObject(sqlForBankId, paramsForBankId, Integer.class);

        String sqlForBankClients = "INSERT INTO bank_clients (UserId, BankId) VALUES (:userId, :bankId)";
        var paramsForBankClients = new MapSqlParameterSource();
        paramsForBankClients.addValue("userId", id);
        paramsForBankClients.addValue("bankId", bankId);
        _jdbcTemplate.update(sqlForBankClients, paramsForBankClients);

        String sql = "INSERT INTO accounts (UserId, bankid, balance, type) VALUES (:userId, :bankId, :balance, :type)";
        var params = new MapSqlParameterSource();
        params.addValue("userId", id);
        params.addValue("bankId", bankId);
        params.addValue("balance", balance);
        params.addValue("type", "DebitAccount");
        _jdbcTemplate.update(sql, params);
    }

    @Transactional
    public void AddDepositAccount(Integer id, BigDecimal balance, LocalDate depositEndDate, String bankName)
    {
        String sqlForBankId = "SELECT Id FROM banks WHERE Name = :bankName";
        var paramsForBankId = new MapSqlParameterSource();
        paramsForBankId.addValue("bankName", bankName);
        Integer bankId = _jdbcTemplate.queryForObject(sqlForBankId, paramsForBankId, Integer.class);

        String sqlForBankClients = "INSERT INTO bank_clients (UserId, BankId) VALUES (:userId, :bankId)";
        var paramsForBankClients = new MapSqlParameterSource();
        paramsForBankClients.addValue("userId", id);
        paramsForBankClients.addValue("bankId", bankId);
        _jdbcTemplate.update(sqlForBankClients, paramsForBankClients);

        String sql = "INSERT INTO accounts (UserId, bankid, balance, depositEndDate, type) VALUES (:userId, :bankId, :balance, :depositEndDate, :type)";
        var params = new MapSqlParameterSource();
        params.addValue("userId", id);
        params.addValue("bankId", bankId);
        params.addValue("balance", balance);
        params.addValue("depositEndDate", depositEndDate);
        params.addValue("type", "DepositAccount");
        _jdbcTemplate.update(sql, params);
    }

}
