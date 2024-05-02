package org.lab3.controllers;

import org.lab3.databaseMenegment.AppConfig;
import org.lab3.models.Cat;
import org.lab3.repositories.CatRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class DatabaseCatController
{
    private final CatRepository repository;

    public DatabaseCatController()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(CatRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        repository = context.getBean(CatRepository.class);
    }

    public Cat getCatById(int id)
    {
        return repository.getCatById(id);
    }

    public List<Cat> getCatsByName(String name)
    {
        return repository.getCatsByName(name);
    }

    public List<Cat> getCatsByBirthDate(LocalDate birthDate)
    {
        return repository.getCatsByBirthDate(birthDate);
    }

    public List<Cat> getCatsByBreed(String breed)
    {
        return repository.getCatsByBreed(breed);
    }

    public List<Cat> getCatsByColor(String color)
    {
        return repository.getCatsByColor(color);
    }

    public void updateCatName(int id, String newName)
    {
        repository.updateCatName(id, newName);
    }

    public void updateCatBirthDate(int id, LocalDate newBirthDate)
    {
        repository.updateCatBirthDate(id, newBirthDate);
    }

    public void updateCatBreed(int id, String breed)
    {
        repository.updateCatBreed(id, breed);
    }

    public void updateCatColor(int id, String color)
    {
        repository.updateCatColor(id, color);
    }

    public void deleteCat(int id)
    {
        repository.deleteCat(id);
    }

    public void addCat(String name, LocalDate birthDate, String breed, String color, int ownerId)
    {
        repository.addCat(name, birthDate, breed, color, ownerId);
    }

    public void addFriendship(int catId1, int catId2)
    {
        repository.addFriendship(catId1, catId2);
    }
}