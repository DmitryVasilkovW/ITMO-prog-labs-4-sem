package org.lab5.catDomain.mappers;

import lombok.experimental.UtilityClass;
import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.dto.CatDto;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CatMapper
{
    public CatDto fromCatDaoToCat(CatDao catDao)
    {
        var friends = new ArrayList<CatDto>();

        if (catDao.getFriends() != null && !catDao.getFriends().isEmpty())
        {
            for (CatDao cat : catDao.getFriends())
            {
                friends.add(fromCatDaoToCat(cat));
            }
        }

        return new CatDto(
                catDao.getId(),
                catDao.getName(),
                catDao.getBirthDate(),
                catDao.getBreed(),
                catDao.getColor(),
                catDao.getOwnerId(),
                friends);
    }

    public List<CatDto> fromCatDaoToCat(List<CatDao> catDaos)
    {
        var cats = new ArrayList<CatDto>();

        for (CatDao catDao : catDaos)
        {
            cats.add(fromCatDaoToCat(catDao));
        }

        return cats;
    }

    public CatDao fromCatToCatDao(CatDto cat)
    {
        var catDao = new CatDao();
        var friends = new ArrayList<CatDao>();

        if (cat.friends() != null && !cat.friends().isEmpty())
        {
            for (CatDto friend : cat.friends())
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

    public List<CatDao> fromCatToCatDao(List<CatDto> cats)
    {
        var catDaos = new ArrayList<CatDao>();

        for (CatDto cat : cats)
        {
            catDaos.add(fromCatToCatDao(cat));
        }

        return catDaos;
    }
}