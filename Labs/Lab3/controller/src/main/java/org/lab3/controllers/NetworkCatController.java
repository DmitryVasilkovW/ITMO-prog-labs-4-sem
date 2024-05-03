package org.lab3.controllers;

import org.lab3.models.Cat;
import org.lab3.repositories.CatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

            if (cat == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cat);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Cat>> getCatsByName(@PathVariable String name)
    {
        try
        {
            List<Cat> cats = repository.getCatsByName(name);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<Cat>> getCatsByBreed(@PathVariable String breed)
    {
        try
        {
            List<Cat> cats = repository.getCatsByBreed(breed);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/birthDate/{birthDate}")
    public ResponseEntity<List<Cat>> getCatsByBirthDate(@PathVariable LocalDate birthDate)
    {
        try
        {
            List<Cat> cats = repository.getCatsByBirthDate(birthDate);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Cat>> getCatsByColor(@PathVariable String color)
    {
        try
        {
            List<Cat> cats = repository.getCatsByColor(color);

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return ResponseEntity.ok(cats);
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

            if (cats.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

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
            Cat newCat = repository.addCat(cat.name(), cat.birthDate(), cat.breed(), cat.color(), cat.ownerId());

            if (newCat == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(newCat);
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
            Cat updatedCat = repository.updateCat(id, cat.name(), cat.birthDate(), cat.breed(), cat.color(), cat.ownerId());

            if (updatedCat == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

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