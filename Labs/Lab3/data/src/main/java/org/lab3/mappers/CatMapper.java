package org.lab3.mappers;

import org.lab3.dao.CatDao;
import org.lab3.models.Cat;

import java.util.ArrayList;
import java.util.List;

public class CatMapper
{
    public Cat fromCatDaoToCat(CatDao catDao)
    {
        var friends = new ArrayList<Cat>();

        if (catDao.getFriends() != null && !catDao.getFriends().isEmpty())
        {
            for (CatDao cat : catDao.getFriends())
            {
                friends.add(fromCatDaoToCat(cat));
            }
        }

        return new Cat(
                catDao.getId(),
                catDao.getName(),
                catDao.getBirthDate(),
                catDao.getBreed(),
                catDao.getColor(),
                catDao.getOwnerId(),
                friends);
    }

    public List<Cat> fromCatDaoToCat(List<CatDao> catDaos)
    {
        var cats = new ArrayList<Cat>();

        for (CatDao catDao : catDaos)
        {
            cats.add(fromCatDaoToCat(catDao));
        }

        return cats;
    }
    
    public CatDao fromCatToCatDao(Cat cat)
    {
        var catDao = new CatDao();
        var friends = new ArrayList<CatDao>();

        if (cat.friends() != null && !cat.friends().isEmpty())
        {
            for (Cat friend : cat.friends())
            {
                friends.add(new CatMapper().fromCatToCatDao(friend));
            }
        }

        catDao.setId(cat.id());
        catDao.setName(cat.name());
        catDao.setBirthDate(cat.birthDate());
        catDao.setBreed(cat.breed());
        catDao.setColor(cat.color());
        catDao.setOwnerId(cat.ownerId());
        catDao.setFriends(friends);

        return catDao;
    }

    public List<CatDao> fromCatToCatDao(List<Cat> cats)
    {
        var catDaos = new ArrayList<CatDao>();

        for (Cat cat : cats)
        {
            catDaos.add(fromCatToCatDao(cat));
        }

        return catDaos;
    }
}