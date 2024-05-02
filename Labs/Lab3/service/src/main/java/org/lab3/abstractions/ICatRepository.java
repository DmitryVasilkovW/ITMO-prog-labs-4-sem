package org.lab3.abstractions;

import org.lab3.models.Cat;

import java.time.LocalDate;
import java.util.List;

public interface ICatRepository
{
    Cat getCatById(int id);
    List<Cat> getCatsByName(String name);
    List<Cat> getCatsByBirthDate(LocalDate birthDate);
    List<Cat> getCatsByBreed(String breed);
    List<Cat> getCatsByColor(String color);
    void updateCatName(int id, String newName);
    void updateCatBirthDate(int id, LocalDate newBirthDate);
    void updateCatBreed(int id, String breed);
    void updateCatColor(int id, String color);
    void deleteCat(int id);
    Cat addCat(String name, LocalDate birthDate, String breed, String color, int ownerId);
    void addFriendship(int catId1, int catId2);
    List<Cat> getAllCats();
    Cat updateCat(int id, String name, LocalDate birthday, String breed, String color, int owner_id);
}

