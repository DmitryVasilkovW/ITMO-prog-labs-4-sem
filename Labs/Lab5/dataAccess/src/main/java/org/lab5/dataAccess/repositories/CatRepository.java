package org.lab5.dataAccess.repositories;


import org.lab5.dataAccess.dao.CatDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    Optional<CatDao> findById(long id);;

    void deleteById(long id);
}

