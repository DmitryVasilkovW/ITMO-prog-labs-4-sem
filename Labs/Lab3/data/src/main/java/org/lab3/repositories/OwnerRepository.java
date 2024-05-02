package org.lab3.repositories;

import org.lab3.abstractions.IOwnerRepository;
import org.lab3.models.Owner;
import org.lab3.rowDataMappers.OwnerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Repository
public class OwnerRepository implements IOwnerRepository
{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OwnerRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Owner getOwnerById(int id)
    {
        String sql = "SELECT * FROM owners WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, new OwnerRowMapper());
    }

    @Override
    @Transactional
    public List<Owner> getAllOwners()
    {
        String sql = "SELECT * FROM owners";

        return jdbcTemplate.query(sql, new OwnerRowMapper());
    }

    @Override
    @Transactional
    public List<Owner> getOwnersByName(String name)
    {
        String sql = "SELECT * FROM owners WHERE name = :name";
        var params = new MapSqlParameterSource();

        params.addValue("name", name);

        return jdbcTemplate.query(sql, params, new OwnerRowMapper());
    }

    @Override
    @Transactional
    public List<Owner> getOwnersByBirthDate(LocalDate birthDate)
    {
        String sql = "SELECT * FROM owners WHERE birthDate = :birthDate";
        var params = new MapSqlParameterSource();

        params.addValue("birthDate", birthDate);

        return jdbcTemplate.query(sql, params, new OwnerRowMapper());
    }

    @Override
    @Transactional
    public void updateOwnerName(int id, String newName)
    {
        String sql = "UPDATE owners SET name = :newName WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("newName", newName);
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public Owner updateOwner(int id, String newName, LocalDate birthDate)
    {
        String sql = "UPDATE owners SET name = :newName, birthdate = :birthDate WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("newName", newName);
        params.addValue("birthDate", birthDate);
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);

        return getOwnerById(id);
    }

    @Override
    @Transactional
    public void updateOwnerBirthDate(int id, LocalDate newBirthDate)
    {
        String sql = "UPDATE owners SET birthDate = :newBirthDate WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("newBirthDate", newBirthDate);
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void deleteOwner(int ownerId)
    {
        String sql1 = "DELETE FROM owners_cats WHERE owner_id = :ownerId";
        String sql2 = "DELETE FROM cats WHERE owner_id = :ownerId";
        String sql3 = "DELETE FROM owners WHERE id = :ownerId";
        var params = new MapSqlParameterSource();

        params.addValue("ownerId", ownerId);

        jdbcTemplate.update(sql1, params);
        jdbcTemplate.update(sql2, params);
        jdbcTemplate.update(sql3, params);
    }

    @Override
    @Transactional
    public Owner addOwner(String name, LocalDate birthDate)
    {
        String sql = "INSERT INTO owners (name, birthdate) VALUES (:name, :birthdate) RETURNING id";
        var params = new MapSqlParameterSource();

        params.addValue("name", name);
        params.addValue("birthdate", birthDate);

        int id = jdbcTemplate.queryForObject(sql, params, Integer.class);

        return getOwnerById(id);
    }
}