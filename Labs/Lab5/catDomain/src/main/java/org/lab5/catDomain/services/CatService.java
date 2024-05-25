package org.lab5.catDomain.services;

import org.lab5.dataAccess.dto.CatDto;

public interface CatService
{
    CatDto createCat(CatDto catDto);
    void getCatById(int id);
    void findAllKitties(int trash);
    void removeCat(long id);
    void findKittiesByBreed(String breed);
    void findKittiesByColour(String colour);
}
