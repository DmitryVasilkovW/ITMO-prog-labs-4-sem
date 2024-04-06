package services;

import abstractions.IOwnerRepository;
import models.Owner;

import java.time.LocalDate;
import java.util.List;

public class OwnerService
{
    IOwnerRepository _repository;

    public OwnerService(IOwnerRepository repository)
    {
        _repository = repository;
    }

    public Owner getOwnerById(int id)
    {
        return _repository.getOwnerById(id);
    }

    public List<Owner> getOwnersByName(String name)
    {
        return _repository.getOwnersByName(name);
    }

    public List<Owner> getOwnersByBirthDate(LocalDate birthDate)
    {
        return _repository.getOwnersByBirthDate(birthDate);
    }

    public void updateOwnerName(int id, String newName)
    {
        _repository.updateOwnerName(id, newName);
    }

    public void updateOwnerBirthDate(int id, LocalDate newBirthDate)
    {
        _repository.updateOwnerBirthDate(id, newBirthDate);
    }

    public void deleteOwner(int ownerId)
    {
        _repository.deleteOwner(ownerId);
    }
}
