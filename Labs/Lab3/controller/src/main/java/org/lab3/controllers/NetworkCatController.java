package org.lab3.controllers;

import org.lab3.models.Cat;
import org.lab3.repositories.CatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
public class NetworkCatController
{
    private final CatRepository repository;

    public NetworkCatController(CatRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cat> getCat(@PathVariable int id)
    {
        try
        {
            Cat cat = repository.getCatById(id);

            return ResponseEntity.ok(cat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cat>> getAllCats()
    {
        try
        {
            List<Cat> cats = repository.getAllCats();

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Cat> createCat(@RequestBody Cat cat)
    {
        try
        {
            repository.addCat(cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), cat.getOwner().getId());

            return ResponseEntity.ok(cat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable int id, @RequestBody Cat cat)
    {
        try
        {
            Cat updatedCat = repository.updateCat(id, cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColor(), cat.getOwner().getId());

            return ResponseEntity.ok(updatedCat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable int id)
    {
        try
        {
            repository.deleteCat(id);

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}