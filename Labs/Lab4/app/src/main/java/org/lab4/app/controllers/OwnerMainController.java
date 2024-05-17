package org.lab4.app.controllers;

import org.lab4.app.models.OwnerDao;
import org.lab4.app.myExceptions.RoleNotSuitableException;
import org.lab4.app.repositories.OwnerRepository;
import org.lab4.app.services.UserDetailsIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDao> getOwner(@PathVariable Long id)
    {
        try
        {
            OwnerDao owner = ownerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));

            if (owner == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(owner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<OwnerDao>> getOwnersByName(@PathVariable String name)
    {
        try
        {
            List<OwnerDao> owners = ownerRepository.findAllByName(name).orElseThrow(() -> new UsernameNotFoundException("Users not found"));

            if (owners.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(owners);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/birthDate/{birthDate}")
    public ResponseEntity<List<OwnerDao>> getOwnersByBirthDate(@PathVariable LocalDate birthDate)
    {
        try
        {
            List<OwnerDao> owners = ownerRepository.findAllByBirthDate(birthDate);

            if (owners.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(owners);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<OwnerDao> createOwner(@RequestBody OwnerDao owner)
    {
        try
        {
            OwnerDao newOwner = ownerRepository.save(owner);

            if (newOwner == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(newOwner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDao> updateOwner(@PathVariable int id, @RequestBody OwnerDao owner)
    {
        try
        {
            OwnerDao newOwner = ownerRepository.save(owner);

            if (newOwner == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(newOwner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id)
    {
        try
        {
            ownerRepository.deleteOwnerDaoById(id);

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}