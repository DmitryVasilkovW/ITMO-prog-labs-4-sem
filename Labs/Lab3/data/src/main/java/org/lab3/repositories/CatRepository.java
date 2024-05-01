package org.lab3.repositories;

import org.lab3.abstractions.ICatRepository;
import org.lab3.models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CatRepository implements ICatRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CatRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Cat> catRowMapper = (rs, rowNum) ->
    {
        Cat cat = new Cat();
        cat.setId(rs.getInt("id"));
        cat.setName(rs.getString("name"));
        cat.setBirthDate(rs.getDate("birthdate").toLocalDate());
        cat.setBreed(rs.getString("breed"));
        cat.setColor(rs.getString("color"));
        return cat;
    };

    @Override
    @Transactional
    public Cat getCatById(int id)
    {
        String sql = "SELECT * FROM cats WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return jdbcTemplate.queryForObject(sql, params, catRowMapper);
    }

    @Override
    @Transactional
    public List<Cat> getCatsByName(String name)
    {
        String sql = "SELECT * FROM cats WHERE name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbcTemplate.query(sql, params, catRowMapper);
    }

    @Override
    @Transactional
    public List<Cat> getCatsByBirthDate(LocalDate birthDate)
    {
        String sql = "SELECT * FROM cats WHERE birthDate = :birthDate";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("birthDate", birthDate);
        return jdbcTemplate.query(sql, params, catRowMapper);
    }

    @Override
    @Transactional
    public List<Cat> getCatsByBreed(String breed)
    {
        String sql = "SELECT * FROM cats WHERE breed = :breed";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("breed", breed);
        return jdbcTemplate.query(sql, params, catRowMapper);
    }

    @Override
    @Transactional
    public List<Cat> getCatsByColor(String color)
    {
        String sql = "SELECT * FROM cats WHERE color = :color";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("color", color);
        return jdbcTemplate.query(sql, params, catRowMapper);
    }

    @Override
    @Transactional
    public void updateCatName(int id, String newName)
    {
        String sql = "UPDATE cats SET name = :newName WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("newName", newName);
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void updateCatBirthDate(int id, LocalDate newBirthDate)
    {
        String sql = "UPDATE cats SET birthDate = :newBirthDate WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("newBirthDate", newBirthDate);
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void updateCatBreed(int id, String breed)
    {
        String sql = "UPDATE cats SET breed = :breed WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("breed", breed);
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void updateCatColor(int id, String color)
    {
        String sql = "UPDATE cats SET color = :color WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("color", color);
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void deleteCat(int id)
    {
        String sql = "DELETE FROM cats WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void addCat(String name, LocalDate birthDate, String breed, String color, int ownerId)
    {
        String sql = "INSERT INTO cats (name, birthdate, breed, color, owner_id) VALUES (:name, :birthdate, :breed, :color, :ownerId)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("birthdate", birthDate);
        params.addValue("breed", breed);
        params.addValue("color", color);
        params.addValue("ownerId", ownerId);
        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void addFriendship(int catId1, int catId2)
    {
        String sql = "INSERT INTO cats_friends (cat_id, friend_id) VALUES (:catId1, :catId2), (:catId2, :catId1)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("catId1", catId1);
        params.addValue("catId2", catId2);
        jdbcTemplate.update(sql, params);
    }
}