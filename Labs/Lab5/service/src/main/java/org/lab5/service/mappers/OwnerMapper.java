package org.lab5.service.mappers;

import lombok.experimental.UtilityClass;
import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.dao.OwnerDao;
import org.lab5.dataAccess.dto.CatDto;
import org.lab5.dataAccess.dto.OwnerDto;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OwnerMapper
{
    public OwnerDto fromOwnerDaoToOwner(OwnerDao ownerDao)
    {
        var cats = new ArrayList<CatDto>();

        if (ownerDao.getCats() != null && !ownerDao.getCats().isEmpty())
        {
            for (CatDao CatDto : ownerDao.getCats())
            {
                cats.add(CatMapper.fromCatDaoToCat(CatDto));
            }
        }

        return new OwnerDto(
                ownerDao.getId(),
                ownerDao.getName(),
                ownerDao.getPassword(),
                ownerDao.getEmail(),
                ownerDao.getBirthDate(),
                ownerDao.getRole(),
                cats);
    }

    public List<OwnerDto> fromOwnerDaoToOwner(List<OwnerDao> ownerDaos)
    {
        var owners = new ArrayList<OwnerDto>();

        for (OwnerDao ownerDao : ownerDaos)
        {
            var cats = new ArrayList<CatDto>();

            if (ownerDao.getCats() != null && !ownerDao.getCats().isEmpty())
            {
                for (CatDao CatDto : ownerDao.getCats())
                {
                    cats.add(CatMapper.fromCatDaoToCat(CatDto));
                }
            }

            owners.add(new OwnerDto(
                    ownerDao.getId(),
                    ownerDao.getName(),
                    ownerDao.getPassword(),
                    ownerDao.getEmail(),
                    ownerDao.getBirthDate(),
                    ownerDao.getRole(),
                    cats));
        }

        return owners;
    }

    public OwnerDao fromOwnerToOwnerDao(OwnerDto OwnerDto)
    {
        var ownerDao = new OwnerDao();
        var cats = new ArrayList<CatDao>();

        if (OwnerDto.cats() != null && !OwnerDto.cats().isEmpty())
        {
            for (CatDto CatDto : OwnerDto.cats())
            {
                cats.add(CatMapper.fromCatToCatDao(CatDto));
            }
        }

        ownerDao.setId(OwnerDto.id());
        ownerDao.setName(ownerDao.getName());
        ownerDao.setBirthDate(OwnerDto.birthDate());
        ownerDao.setCats(cats);

        return ownerDao;
    }

    public List<OwnerDao> fromOwnwerToOwnerDao(List<OwnerDto> owners)
    {
        var ownerDaos = new ArrayList<OwnerDao>();

        for (OwnerDto OwnerDto : owners)
        {
            ownerDaos.add(fromOwnerToOwnerDao(OwnerDto));
        }

        return ownerDaos;
    }
}