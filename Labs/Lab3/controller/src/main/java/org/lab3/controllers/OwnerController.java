package org.lab3.controllers;

import org.lab3.databaseMenegment.AppConfig;
import org.lab3.models.Owner;
import org.lab3.repositories.OwnerRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.util.List;

public class OwnerController
{
    private OwnerRepository repository;

    public OwnerController()
    {
        var context = new AnnotationConfigApplicationContext();

        context.register(OwnerRepository.class);
        context.register(AppConfig.class);
        context.refresh();

        repository = context.getBean(OwnerRepository.class);
    }

    public Owner getOwnerById(int id)
    {
        return repository.getOwnerById(id);
    }

    public List<Owner> getOwnersByName(String name)
    {
        return repository.getOwnersByName(name);
    }

    public List<Owner> getOwnersByBirthDate(LocalDate birthDate)
    {
        return repository.getOwnersByBirthDate(birthDate);
    }

    public void updateOwnerName(int id, String newName)
    {
        repository.updateOwnerName(id, newName);
    }

    public void updateOwnerBirthDate(int id, LocalDate newBirthDate)
    {
        repository.updateOwnerBirthDate(id, newBirthDate);
    }

    public void deleteOwner(int ownerId)
    {
        repository.deleteOwner(ownerId);
    }

    public void addOwner(String name, LocalDate birthDate)
    {
        repository.addOwner(name, birthDate);
    }
}