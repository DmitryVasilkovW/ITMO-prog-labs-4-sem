package org.lab3.repositories;

import org.lab3.abstractions.IOwnerRepository;
import org.lab3.dao.CatDao;
import org.lab3.dao.OwnerDao;
import org.lab3.databaseMenegment.AppConfig;
import org.lab3.mappers.CatMapper;
import org.lab3.mappers.OwnerMapper;
import org.lab3.models.Owner;
import org.lab3.rowDataMappers.CatRowMapper;
import org.lab3.rowDataMappers.OwnerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Repository
public class OwnerRepository implements IOwnerRepository
{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final OwnerMapper ownerMapper = new OwnerMapper();

    @Autowired
    public OwnerRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    protected List<CatDao> getCatsByOwnerId(int ownerId)
    {
        var context = new AnnotationConfigApplicationContext();
        var catMapper = new CatMapper();

        context.register(CatRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        var catRepository = context.getBean(CatRepository.class);
        String sql = "SELECT * FROM cats WHERE owner_id =:ownerId";
        var params = new MapSqlParameterSource();

        params.addValue("ownerId", ownerId);

        List<CatDao> cats = jdbcTemplate.query(sql, params, new CatRowMapper());

        if (cats != null && !cats.isEmpty())
        {
            for (CatDao cat : cats)
            {
                String friendsSql = "SELECT friend_id FROM cats_friends WHERE cat_id = :id";

                var friendsParams = new MapSqlParameterSource(); // Создаем новый экземпляр MapSqlParameterSource
                friendsParams.addValue("id", cat.getId());

                List<Integer> friendIds = jdbcTemplate.queryForList(friendsSql, friendsParams, Integer.class);

                var friends = new ArrayList<CatDao>();

                if (friendIds != null && !friendIds.isEmpty())
                {
                    for (Integer friendId : friendIds)
                    {
                        friends.add(catMapper.fromCatToCatDao(catRepository.getCatById(friendId)));
                    }
                }

                cat.setFriends(friends);
            }
        }

        return cats;
    }


    @Override
    @Transactional
    public Owner getOwnerById(int id)
    {
        String sql = "SELECT * FROM owners WHERE id = :id";
        var params = new MapSqlParameterSource();

        params.addValue("id", id);

        OwnerDao ownerDao = jdbcTemplate.queryForObject(sql, params, new OwnerRowMapper());

        ownerDao.setCats(getCatsByOwnerId(id));

        return ownerMapper.fromOwnerDaoToOwner(ownerDao);
    }

    @Override
    @Transactional
    public List<Owner> getAllOwners()
    {
        String sql = "SELECT * FROM owners";

        List<OwnerDao> owners = jdbcTemplate.query(sql, new OwnerRowMapper());

        if (owners != null && !owners.isEmpty())
        {
            for (OwnerDao owner : owners)
            {
                owner.setCats(getCatsByOwnerId(owner.getId()));
            }
        }

        return ownerMapper.fromOwnerDaoToOwner(owners);
    }

    @Override
    @Transactional
    public List<Owner> getOwnersByName(String name)
    {
        String sql = "SELECT * FROM owners WHERE name = :name";
        var params = new MapSqlParameterSource();

        params.addValue("name", name);

        List<OwnerDao> owners = jdbcTemplate.query(sql, params, new OwnerRowMapper());

        if (owners != null && !owners.isEmpty())
        {
            for (OwnerDao owner : owners)
            {
                owner.setCats(getCatsByOwnerId(owner.getId()));
            }
        }

        return ownerMapper.fromOwnerDaoToOwner(owners);
    }

    @Override
    @Transactional
    public List<Owner> getOwnersByBirthDate(LocalDate birthDate)
    {
        String sql = "SELECT * FROM owners WHERE birthdate = :birthDate";
        var params = new MapSqlParameterSource();

        params.addValue("birthDate", birthDate);

        List<OwnerDao> owners = jdbcTemplate.query(sql, params, new OwnerRowMapper());

        if (owners != null && !owners.isEmpty())
        {
            for (OwnerDao owner : owners)
            {
                owner.setCats(getCatsByOwnerId(owner.getId()));
            }
        }

        return ownerMapper.fromOwnerDaoToOwner(owners);
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