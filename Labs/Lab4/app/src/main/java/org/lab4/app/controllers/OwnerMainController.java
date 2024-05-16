package org.lab4.app.controllers;

import org.lab4.app.models.OwnerDao;
import org.lab4.app.myExceptions.RoleNotSuitableException;
import org.lab4.app.repositories.OwnerRepository;
import org.lab4.app.services.UserDetailsIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerMainController
{
    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerMainController(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping
    public OwnerDao userAccess(Authentication authentication)
    {
        if (authentication == null) return null;

        UserDetailsIml userDetails = (UserDetailsIml) authentication.getPrincipal();

        try
        {
            var owner = ownerRepository.findByEmail(userDetails.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            owner.setPassword("********");

            return owner;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/owners")
    public List<OwnerDao> adminAccess(Authentication authentication)
    {
        try
        {
            UserDetailsIml userDetails = (UserDetailsIml) authentication.getPrincipal();
            if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Admin")))
            {
                throw new RoleNotSuitableException("You don't have sufficient rights");
            }

            List<OwnerDao> owners = ownerRepository.findAll();

            if(owners.isEmpty())
            {
                throw new UsernameNotFoundException("Users not found");
            }

            return owners;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }

}
