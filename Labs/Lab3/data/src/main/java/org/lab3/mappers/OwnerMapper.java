package org.lab3.mappers;

import org.lab3.dao.CatDao;
import org.lab3.dao.OwnerDao;
import org.lab3.models.Cat;
import org.lab3.models.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerMapper
{
    public Owner fromOwnerDaoToOwner(OwnerDao ownerDao)
    {
        var cats = new ArrayList<Cat>();

        if (ownerDao.getCats() != null && !ownerDao.getCats().isEmpty())
        {
            for (CatDao cat : ownerDao.getCats())
            {
                cats.add(new CatMapper().fromCatDaoToCat(cat));
            }
        }

        return new Owner(ownerDao.getId(), ownerDao.getName(), ownerDao.getBirthDate(), cats);
    }

    public List<Owner> fromOwnerDaoToOwner(List<OwnerDao> ownerDaos)
    {
        var owners = new ArrayList<Owner>();

        for (OwnerDao ownerDao : ownerDaos)
        {
            var cats = new ArrayList<Cat>();

            if (ownerDao.getCats() != null && !ownerDao.getCats().isEmpty())
            {
                for (CatDao cat : ownerDao.getCats())
                {
                    cats.add(new CatMapper().fromCatDaoToCat(cat));
                }
            }

            owners.add(new Owner(ownerDao.getId(), ownerDao.getName(), ownerDao.getBirthDate(), cats));
        }

        return owners;
    }

    public OwnerDao fromOwnerToOwnerDao(Owner owner)
    {
        var ownerDao = new OwnerDao();
        var cats = new ArrayList<CatDao>();

        if (owner.cats() != null && !owner.cats().isEmpty())
        {
            for (Cat cat : owner.cats())
            {
                cats.add(new CatMapper().fromCatToCatDao(cat));
            }
        }

        ownerDao.setId(owner.id());
        ownerDao.setName(ownerDao.getName());
        ownerDao.setBirthDate(owner.birthDate());
        ownerDao.setCats(cats);

        return ownerDao;
    }

    public List<OwnerDao> fromOwnwerToOwnerDao(List<Owner> owners)
    {
        var ownerDaos = new ArrayList<OwnerDao>();

        for (Owner owner : owners)
        {
            ownerDaos.add(fromOwnerToOwnerDao(owner));
        }

        return ownerDaos;
    }
}