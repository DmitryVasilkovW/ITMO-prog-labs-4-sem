package org.lab5.catDomain.services;

import lombok.experimental.ExtensionMethod;
import org.lab5.catDomain.mappers.CatMapper;
import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.dto.CatDto;
import org.lab5.dataAccess.dto.CatList;
import org.lab5.dataAccess.repositories.CatRepository;
import org.lab5.dataAccess.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@ExtensionMethod(CatMapper.class)
@ComponentScan(basePackages = "org.lab5")
public class CatServiceImpl implements CatService
{
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;
    private final KafkaTemplate<String, CatDto> getCat;
    private final KafkaTemplate<String, CatList> getCats;

    @Autowired
    public CatServiceImpl(CatRepository catRepository, OwnerRepository ownerRepository, KafkaTemplate<String, CatDto> getCat, KafkaTemplate<String, CatList> getCats) {
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
        this.getCat = getCat;
        this.getCats = getCats;
    }


    @KafkaListener(topics = "create_cat", groupId = "groupIdCK", containerFactory = "createCatFactory")
    public CatDto createCat(CatDto catDto)
    {
        CatDao cat =  catRepository.save(CatMapper.fromCatToCatDao(catDto));

        return CatMapper.fromCatDaoToCat(cat);
    }


    @KafkaListener(topics = "get cat_by_id", groupId = "groupIdGBIDK", containerFactory = "byIdCatFactory")
    public void getCatById(int id)
    {
        getCat.send("got cat_by_id", CatMapper.fromCatDaoToCat(catRepository.findById(id).orElseThrow(() -> new RuntimeException())));
    }

    @KafkaListener(topics = "get_cats", groupId = "groupIdGBIDKs", containerFactory = "byIdCatFactory")
    public void findAllKitties(int trash)
    {
        var cats = (ArrayList<CatDto>) CatMapper.fromCatDaoToCat(catRepository.findAll());

        getCats.send("got_cats", new CatList(cats));
    }

    @KafkaListener(topics = "remove_Cat", groupId = "groupIdRK", containerFactory = "byIdCatFactory")
    public void removeCat(long id)
    {
        catRepository.deleteById(id);
    }

    @KafkaListener(topics = "get_kitties_by_breed", groupId = "groupByBreed", containerFactory = "filterFactory")
    public void findKittiesByBreed(String breed)
    {
        var cats = (ArrayList<CatDto>) CatMapper.fromCatDaoToCat(catRepository.findAllByBreed(breed));

        getCats.send("got_cats_by_breed", new CatList(cats));
    }

    @KafkaListener(topics = "get_kitties_by_colour", groupId = "groupByColour", containerFactory = "filterFactory")
    public void findKittiesByColour(String colour)
    {
        var cats = (ArrayList<CatDto>) CatMapper.fromCatDaoToCat(catRepository.findAllByColor(colour));

        getCats.send("got_cats_by_color", new CatList(cats));
    }
}