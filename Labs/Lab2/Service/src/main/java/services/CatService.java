package services;

import abstractions.ICatRepository;
import models.Cat;

import java.time.LocalDate;
import java.util.List;

public class CatService
{
    private ICatRepository _repository;

    public CatService(ICatRepository repository)
    {
        _repository = repository;
    }

    public Cat getCatById(int id)
    {
        return _repository.getCatById(id);
    }

    public List<Cat> getCatsByName(String name)
    {
        return _repository.getCatsByName(name);
    }

    public List<Cat> getCatsByBirthDate(LocalDate birthDate)
    {
        return _repository.getCatsByBirthDate(birthDate);
    }

    public List<Cat> getCatsByBreed(String breed)
    {
        return _repository.getCatsByBreed(breed);
    }

    public List<Cat> getCatsByColor(String color)
    {
        return _repository.getCatsByColor(color);
    }

    public void updateCatName(int id, String newName)
    {
        _repository.updateCatName(id, newName);
    }

    public void updateCatBirthDate(int id, LocalDate newBirthDate)
    {
        _repository.updateCatBirthDate(id, newBirthDate);
    }

    public void updateCatBreed(int id, String breed)
    {
        _repository.updateCatBreed(id, breed);
    }

    public void updateCatColor(int id, String color)
    {
        _repository.updateCatColor(id, color);
    }

    public void deleteCat(int id)
    {
        _repository.deleteCat(id);
    }

    public void addCat(String name, LocalDate birthDate, String breed, String color, int ownerId)
    {
        _repository.addCat(name, birthDate, breed, color, ownerId);
    }

    public void addFriendship(int catId1, int catId2)
    {
        _repository.addFriendship(catId1, catId2);
    }
}
