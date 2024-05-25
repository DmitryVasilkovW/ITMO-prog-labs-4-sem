package org.lab5.service.services;

import lombok.experimental.ExtensionMethod;
import org.lab5.dataAccess.dao.OwnerDao;
import org.lab5.dataAccess.dto.OwnerDto;
import org.lab5.dataAccess.repositories.OwnerRepository;
import org.lab5.service.mappers.CatMapper;
import org.lab5.service.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@ExtensionMethod({OwnerMapper.class, CatMapper.class})
public class OwnerServiceImpl
{
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    public OwnerDto createOwner(String name, LocalDate birthDate)
    {
        var owner = new OwnerDao();

        owner.setName(name);
        owner.setBirthDate(birthDate);

        ownerRepository.save(owner);
        return OwnerMapper.fromOwnerDaoToOwner(owner);
    }

    public OwnerDto getOwnerById(long id)
    {
        return OwnerMapper.fromOwnerDaoToOwner(ownerRepository.getOne(id));
    }

    public List<OwnerDto> findAllOwners()
    {
        return OwnerMapper.fromOwnerDaoToOwner(ownerRepository.findAll());
    }

    public void removeOwner(long id)
    {
        ownerRepository.deleteById(id);
    }
}
