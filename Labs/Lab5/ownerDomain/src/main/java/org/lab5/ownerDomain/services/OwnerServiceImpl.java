package org.lab5.ownerDomain.services;

import lombok.experimental.ExtensionMethod;
import org.lab5.dataAccess.dao.OwnerDao;
import org.lab5.dataAccess.dto.CatList;
import org.lab5.dataAccess.dto.OwnerDto;
import org.lab5.dataAccess.dto.OwnerListDto;
import org.lab5.dataAccess.repositories.OwnerRepository;
import org.lab5.ownerDomain.mappers.CatMapper;
import org.lab5.ownerDomain.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@ExtensionMethod({OwnerMapper.class, CatMapper.class})
public class OwnerServiceImpl implements OwnerService
{
    private final OwnerRepository ownerRepository;
    private final KafkaTemplate<String, OwnerDto> getOwner;
    private final KafkaTemplate<String, OwnerListDto> getOwners;
    private final KafkaTemplate<String, CatList> getKitties;

    @Autowired
    public OwnerServiceImpl(
            OwnerRepository ownerRepository,
            KafkaTemplate<String, OwnerDto> getOwner,
            KafkaTemplate<String, OwnerListDto> getOwners,
            KafkaTemplate<String, CatList> getKitties)
    {
        this.ownerRepository = ownerRepository;
        this.getOwner = getOwner;
        this.getOwners = getOwners;
        this.getKitties = getKitties;
    }

    @KafkaListener(topics = "create_owner", groupId = "groupIdCO", containerFactory = "createOwnerFactory")
    public OwnerDto createOwner(OwnerDto ownerDto)
    {
        OwnerDao owner = ownerRepository.save(OwnerMapper.fromOwnerToOwnerDao(ownerDto));

        return OwnerMapper.fromOwnerDaoToOwner(owner);
    }

    @KafkaListener(topics = "get_by_id_owner", groupId = "groupIdGBIDO", containerFactory = "byIdOwnerFactory")
    public void getOwnerById(long id)
    {
        OwnerDao owner = ownerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("no such owner"));

        getOwner.send("got_by_id_owner", OwnerMapper.fromOwnerDaoToOwner(owner));
    }

    @KafkaListener(topics = "get_owners", groupId = "groupIdGO", containerFactory = "byIdOwnerFactory")
    public void findAllOwners(int trash)
    {
        var owners = (ArrayList<OwnerDto>) OwnerMapper.fromOwnerDaoToOwner(ownerRepository.findAll());

        getOwners.send("got_owners", new OwnerListDto(owners));
    }

    @KafkaListener(topics = "remove_owner", groupId = "groupIdRO", containerFactory = "byIdOwnerFactory")
    public void removeOwner(long id)
    {
        ownerRepository.deleteById(id);
    }
}
