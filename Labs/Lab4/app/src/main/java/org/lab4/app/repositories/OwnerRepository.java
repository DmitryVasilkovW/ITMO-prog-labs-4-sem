package org.lab4.app.repositories;

import org.lab4.app.models.OwnerDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerDao, Long>
{
    Optional<OwnerDao> findByEmail(String email);

    Optional<OwnerDao> findByName(String name);

    List<OwnerDao> findAll();

    Boolean existsOwnerByEmail(String email);

    Boolean existsByname(String username);
}
