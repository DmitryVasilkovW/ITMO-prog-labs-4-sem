package controllers;

import models.Cat;
import services.CatService;

import java.time.LocalDate;
import java.util.List;

public class CatController
{
    private CatService _service;
    
    public CatController(CatService service)
    {
        _service = service;
    }

    public Cat getCatById(int id)
    {
        return _service.getCatById(id);
    }

    public List<Cat> getCatsByName(String name)
    {
        return _service.getCatsByName(name);
    }

    public List<Cat> getCatsByBirthDate(LocalDate birthDate)
    {
        return _service.getCatsByBirthDate(birthDate);
    }

    public List<Cat> getCatsByBreed(String breed)
    {
        return _service.getCatsByBreed(breed);
    }

    public List<Cat> getCatsByColor(String color)
    {
        return _service.getCatsByColor(color);
    }

    public void updateCatName(int id, String newName)
    {
        _service.updateCatName(id, newName);
    }

    public void updateCatBirthDate(int id, LocalDate newBirthDate)
    {
        _service.updateCatBirthDate(id, newBirthDate);
    }

    public void updateCatBreed(int id, String breed)
    {
        _service.updateCatBreed(id, breed);
    }

    public void updateCatColor(int id, String color)
    {
        _service.updateCatColor(id, color);
    }

    public void deleteCat(int id)
    {
        _service.deleteCat(id);
    }
}
