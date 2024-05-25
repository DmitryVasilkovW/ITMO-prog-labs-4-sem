package org.lab5.service.services;

import lombok.experimental.ExtensionMethod;
import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.dto.CatDto;
import org.lab5.dataAccess.repositories.CatRepository;
import org.lab5.dataAccess.repositories.OwnerRepository;
import org.lab5.service.mappers.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@ExtensionMethod(CatMapper.class)
public class CatServiceImpl
{
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository)
    {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    public CatDto createCat(String name, LocalDate birthDate, String breedName, String colourName, int ownerId)
    {
        var cat = new CatDao();

        cat.setName(name);
        cat.setBirthDate(birthDate);
        cat.setBreed(breedName);
        cat.setColor(colourName);
        cat.setOwnerId(ownerId);

        return CatMapper.fromCatDaoToCat(cat);
    }

    public CatDto getCatById(long id)
    {
        return CatMapper.fromCatDaoToCat(catRepository.getOne(id));
    }

    public List<CatDto> findAllCats()
    {
        return CatMapper.fromCatDaoToCat(catRepository.findAll());
    }

    public void removeCats(long id)
    {
        catRepository.deleteById(id);
    }

    public List<CatDto> findCatsByBreed(String breedName)
    {
        return CatMapper.fromCatDaoToCat(catRepository.findAllByBreed(breedName));
    }

    public List<CatDto> findCatsByColour(String colourName)
    {
        return CatMapper.fromCatDaoToCat(catRepository.findAllByColor(colourName));
    }
}