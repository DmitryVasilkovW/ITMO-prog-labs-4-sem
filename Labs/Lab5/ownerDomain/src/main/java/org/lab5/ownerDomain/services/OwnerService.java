package org.lab5.ownerDomain.services;

import org.lab5.dataAccess.dto.OwnerDto;

public interface OwnerService
{
    OwnerDto createOwner(OwnerDto ownerDto);
    void getOwnerById(long id);
    void findAllOwners(int trash);
    void removeOwner(long id);
}
