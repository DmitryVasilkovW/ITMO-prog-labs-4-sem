package org.lab3.repositories;

import org.lab3.abstractions.ICatRepository;
import org.lab3.models.Cat;
import org.lab3.rowDataMappers.CatRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional
    public List<Cat> getAllCats()
    {
        String sql = "SELECT cats.id AS cat_id, cats.name AS cat_name, cats.birthdate AS cat_birthdate, cats.breed AS cat_breed, cats.color AS cat_color, owners.id AS owner_id, owners.name AS owner_name, owners.birthdate AS owner_birthdate FROM cats INNER JOIN owners ON cats.owner_id = owners.id";

        return jdbcTemplate.query(sql, new CatRowMapper());
    }

    @Override
    @Transactional
    public Cat updateCat(int id, String name, LocalDate birthday, String breed, String color, int owner_id)
    {
        String sql = "UPDATE cats SET name = :name, birthdate = :birthdate, breed = :breed, color = :color, owner_id = :owner_id WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("id", id);
        params.addValue("name", name);
        params.addValue("birthdate", birthday);
        params.addValue("breed", breed);
        params.addValue("color", color);
        params.addValue("owner_id", owner_id);

        jdbcTemplate.update(sql, params);

        return getCatById(id);
    }


    @Override
    @Transactional
    public Cat getCatById(int id)
    {
        try
        {
            String sql = "SELECT cats.id AS cat_id, cats.name AS cat_name, cats.birthdate AS cat_birthdate, cats.breed AS cat_breed, cats.color AS cat_color, owners.id AS owner_id, owners.name AS owner_name, owners.birthdate AS owner_birthdate FROM cats INNER JOIN owners ON cats.owner_id = owners.id WHERE cats.id = :id";

            var params = new MapSqlParameterSource();

            params.addValue("id", id);

            return jdbcTemplate.queryForObject(sql, params, new CatRowMapper());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    @Transactional
    public List<Cat> getCatsByName(String name)
    {
        String sql = "SELECT cats.id AS cat_id, cats.name AS cat_name, cats.birthdate AS cat_birthdate, cats.breed AS cat_breed, cats.color AS cat_color, owners.id AS owner_id, owners.name AS owner_name, owners.birthdate AS owner_birthdate FROM cats INNER JOIN owners ON cats.owner_id = owners.id WHERE cats.name = :name";

        var params = new MapSqlParameterSource();

        params.addValue("name", name);

        return jdbcTemplate.query(sql, params, new CatRowMapper());
    }

    @Override
    @Transactional
    public List<Cat> getCatsByBirthDate(LocalDate birthDate)
    {
        String sql = "SELECT cats.id AS cat_id, cats.name AS cat_name, cats.birthdate AS cat_birthdate, cats.breed AS cat_breed, cats.color AS cat_color, owners.id AS owner_id, owners.name AS owner_name, owners.birthdate AS owner_birthdate FROM cats INNER JOIN owners ON cats.owner_id = owners.id WHERE cats.birthdate = :birthDate";

        var params = new MapSqlParameterSource();

        params.addValue("birthDate", birthDate);

        return jdbcTemplate.query(sql, params, new CatRowMapper());
    }

    @Override
    @Transactional
    public List<Cat> getCatsByBreed(String breed)
    {
        String sql = "SELECT cats.id AS cat_id, cats.name AS cat_name, cats.birthdate AS cat_birthdate, cats.breed AS cat_breed, cats.color AS cat_color, owners.id AS owner_id, owners.name AS owner_name, owners.birthdate AS owner_birthdate FROM cats INNER JOIN owners ON cats.owner_id = owners.id WHERE cats.breed = :breed";

        var params = new MapSqlParameterSource();

        params.addValue("breed", breed);

        return jdbcTemplate.query(sql, params, new CatRowMapper());
    }


    @Override
    @Transactional
    public List<Cat> getCatsByColor(String color)
    {
        String sql = "SELECT cats.id AS cat_id, cats.name AS cat_name, cats.birthdate AS cat_birthdate, cats.breed AS cat_breed, cats.color AS cat_color, owners.id AS owner_id, owners.name AS owner_name, owners.birthdate AS owner_birthdate FROM cats INNER JOIN owners ON cats.owner_id = owners.id WHERE cats.color = :color";
        var params = new MapSqlParameterSource();

        params.addValue("color", color);

        return jdbcTemplate.query(sql, params, new CatRowMapper());
    }

    @Override
    @Transactional
    public void updateCatName(int id, String newName)
    {
        String sql = "UPDATE cats SET name = :newName WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("newName", newName);
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void updateCatBirthDate(int id, LocalDate newBirthDate)
    {
        String sql = "UPDATE cats SET birthDate = :newBirthDate WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("newBirthDate", newBirthDate);
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void updateCatBreed(int id, String breed)
    {
        try
        {
            String sql = "UPDATE cats SET breed = :breed WHERE id = :id";
            var params = new MapSqlParameterSource();

            params.addValue("breed", breed);
            params.addValue("id", id);

            jdbcTemplate.update(sql, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateCatColor(int id, String color)
    {
        String sql = "UPDATE cats SET color = :color WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("color", color);
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    @Transactional
    public void deleteCat(int id)
    {
        try
        {
            String sql = "DELETE FROM cats WHERE id = :id";
            String sqlForDeleteRelation = "DELETE FROM owners_cats WHERE cat_id = :id";
            var params = new MapSqlParameterSource();

            params.addValue("id", id);

            jdbcTemplate.update(sqlForDeleteRelation, params);
            jdbcTemplate.update(sql, params);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional
    public void addCat(String name, LocalDate birthDate, String breed, String color, int ownerId)
    {
        String sql = "INSERT INTO cats (name, birthdate, breed, color, owner_id) VALUES (:name, :birthdate, :breed, :color, :ownerId) RETURNING id";
        var params = new MapSqlParameterSource();

        params.addValue("name", name);
        params.addValue("birthdate", birthDate);
        params.addValue("breed", breed);
        params.addValue("color", color);
        params.addValue("ownerId", ownerId);

        int catId = jdbcTemplate.queryForObject(sql, params, Integer.class);

        String sqlUpdateOwnersCats = "INSERT INTO owners_cats (cat_id, owner_id) VALUES (:catId, :ownerId)";
        var paramsUpdateOwnersCats = new MapSqlParameterSource();

        paramsUpdateOwnersCats.addValue("catId", catId);
        paramsUpdateOwnersCats.addValue("ownerId", ownerId);

        jdbcTemplate.update(sqlUpdateOwnersCats, paramsUpdateOwnersCats);
    }

    @Override
    @Transactional
    public void addFriendship(int catId1, int catId2)
    {
        String sql = "INSERT INTO cats_friends (cat_id, friend_id) VALUES (:catId1, :catId2), (:catId2, :catId1)";
        var params = new MapSqlParameterSource();

        params.addValue("catId1", catId1);
        params.addValue("catId2", catId2);

        jdbcTemplate.update(sql, params);
    }
}