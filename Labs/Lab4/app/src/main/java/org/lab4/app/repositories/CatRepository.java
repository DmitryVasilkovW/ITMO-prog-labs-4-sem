package org.lab4.app.repositories;

import org.lab4.app.models.CatDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<CatDao, Long>
{

    List<CatDao> findAllByOwnerId(Long id);

    List<CatDao> findAllByNameAndOwnerId(String name, Long id);

    List<CatDao> findAllByBirthDateAndOwnerId(LocalDate birthDate, Long id);

    List<CatDao> findAllByBreedAndOwnerId(String breed, Long id);

    List<CatDao> findAllByColorAndOwnerId(String color, Long id);

    CatDao findByIdAndOwnerId(Long id, Long ownerId);

    List<CatDao> findAllByName(String name);

    List<CatDao> findAllByBirthDate(LocalDate birthDate);

    List<CatDao> findAllByBreed(String breed);

    List<CatDao> findAllByColor(String color);

    List<CatDao> findAll();

    CatDao findById(long id);;

    void deleteById(long id);
}