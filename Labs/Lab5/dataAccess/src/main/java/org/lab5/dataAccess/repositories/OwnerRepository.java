package org.lab5.dataAccess.repositories;

import org.lab5.dataAccess.dao.OwnerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

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

