package controllers;

import models.Owner;
import services.OwnerService;

import java.time.LocalDate;
import java.util.List;

public class OwnerController
{
    private OwnerService _service;

    public OwnerController(OwnerService service)
    {
        _service = service;
    }

    public Owner getOwnerById(int id)
    {
        return _service.getOwnerById(id);
    }

    public List<Owner> getOwnersByName(String name)
    {
        return _service.getOwnersByName(name);
    }

    public List<Owner> getOwnersByBirthDate(LocalDate birthDate)
    {
        return _service.getOwnersByBirthDate(birthDate);
    }

    public void updateOwnerName(int id, String newName)
    {
        _service.updateOwnerName(id, newName);
    }

    public void updateOwnerBirthDate(int id, LocalDate newBirthDate)
    {
        _service.updateOwnerBirthDate(id, newBirthDate);
    }

    public void deleteOwner(int ownerId)
    {
        _service.deleteOwner(ownerId);
    }
}