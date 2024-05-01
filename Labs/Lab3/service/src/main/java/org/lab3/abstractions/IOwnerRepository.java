package org.lab3.abstractions;

import org.lab3.models.Owner;

import java.time.LocalDate;
import java.util.List;

public interface IOwnerRepository
{
    Owner getOwnerById(int id);
    List<Owner> getOwnersByName(String name);
    List<Owner> getOwnersByBirthDate(LocalDate birthDate);
    void updateOwnerName(int id, String newName);
    void updateOwnerBirthDate(int id, LocalDate newBirthDate);
    void deleteOwner(int ownerId);
    void addOwner(String name, LocalDate birthDate);
}