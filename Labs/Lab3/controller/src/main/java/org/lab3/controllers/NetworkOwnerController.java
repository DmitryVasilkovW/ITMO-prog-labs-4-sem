package org.lab3.controllers;

import org.lab3.models.Owner;
import org.lab3.repositories.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class NetworkOwnerController
{
    private final OwnerRepository repository;

    public NetworkOwnerController(OwnerRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable int id)
    {
        try
        {
            Owner owner = repository.getOwnerById(id);

            return ResponseEntity.ok(owner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners()
    {
        try
        {
            List<Owner> owners = repository.getAllOwners();

            return ResponseEntity.ok(owners);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner)
    {
        try
        {
            repository.addOwner(owner.getName(), owner.getBirthDate());

            return ResponseEntity.ok(owner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable int id, @RequestBody Owner owner)
    {
        try
        {
            repository.updateOwner(id, owner.getName(), owner.getBirthDate());

            return ResponseEntity.ok(owner);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable int id) {
        try
        {
            repository.deleteOwner(id);
            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}