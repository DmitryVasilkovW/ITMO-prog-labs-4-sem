package org.lab5.service.services;

import org.lab5.dataAccess.dao.OwnerDao;
import org.lab5.dataAccess.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = {"org.lab4.datajpa.repositories"})
public class UserService implements UserDetailsService
{
    private OwnerRepository ownerRepository;

    @Autowired
    public void setUserRepository(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        OwnerDao owner = ownerRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Owner %s not found", username)
        ));

        return UserDetailsIml.build(owner);
    }
}