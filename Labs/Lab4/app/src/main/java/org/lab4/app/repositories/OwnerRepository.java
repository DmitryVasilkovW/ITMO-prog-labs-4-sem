package org.lab4.app.repositories;

import org.lab4.app.models.OwnerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerDao, Long>
{
    Optional<OwnerDao> findByEmail(String email);

    Optional<OwnerDao> findByName(String name);

    Optional<OwnerDao> findById(Long id);

    List<OwnerDao> findAll();

    List<OwnerDao> findAllByBirthDate(LocalDate birthDate);

    Optional<List<OwnerDao>> findAllByName(String name);

    Boolean existsOwnerByEmail(String email);

    void deleteOwnerDaoById(Long id);
}
