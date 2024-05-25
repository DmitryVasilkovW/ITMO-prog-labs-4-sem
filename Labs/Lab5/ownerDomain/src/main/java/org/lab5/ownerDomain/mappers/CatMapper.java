package org.lab5.ownerDomain.mappers;

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
            for (CatDao CatDto : catDao.getFriends())
            {
                friends.add(fromCatDaoToCat(CatDto));
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

    public CatDao fromCatToCatDao(CatDto CatDto)
    {
        var catDao = new CatDao();
        var friends = new ArrayList<CatDao>();

        if (CatDto.friends() != null && !CatDto.friends().isEmpty())
        {
            for (CatDto friend : CatDto.friends())
            {
                friends.add(new CatMapper().fromCatToCatDao(friend));
            }
        }

        catDao.setId(CatDto.id());
        catDao.setName(CatDto.name());
        catDao.setBirthDate(CatDto.birthDate());
        catDao.setBreed(CatDto.breed());
        catDao.setColor(CatDto.color());
        catDao.setOwnerId(CatDto.ownerId());
        catDao.setFriends(friends);

        return catDao;
    }

    public List<CatDao> fromCatToCatDao(List<CatDto> cats)
    {
        var catDaos = new ArrayList<CatDao>();

        for (CatDto CatDto : cats)
        {
            catDaos.add(fromCatToCatDao(CatDto));
        }

        return catDaos;
    }
}
