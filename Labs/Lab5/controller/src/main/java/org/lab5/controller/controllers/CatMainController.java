package org.lab5.controller.controllers;

import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cats")
public class CatMainController
{
    private final CatRepository catRepository;

    @Autowired
    public CatMainController(CatRepository catRepository)
    {
        this.catRepository = catRepository;
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<CatDao> getCat(@PathVariable Long id)
    {
        try
        {
            CatDao cat = catRepository.findById(id).orElseThrow(() -> new NullPointerException("No such cat"));

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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CatDao>> getCatsByName(@PathVariable String name)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByName(name);

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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<CatDao>> getCatsByBreed(@PathVariable String breed)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByBreed(breed);

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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/birthDate/{birthDate}")
    public ResponseEntity<List<CatDao>> getCatsByBirthDate(@PathVariable LocalDate birthDate)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByBirthDate(birthDate);

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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/color/{color}")
    public ResponseEntity<List<CatDao>> getCatsByColor(@PathVariable String color)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByColor(color);

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

    @PreAuthorize("hasRole('Admin')")
    @GetMapping
    public ResponseEntity<List<CatDao>> getAllCats()
    {
        try
        {
            List<CatDao> cats = catRepository.findAll();

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

    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<CatDao> createCat(@RequestBody CatDao cat)
    {
        try
        {
            CatDao newCat = catRepository.save(cat);

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

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<CatDao> updateCat(@PathVariable int id, @RequestBody CatDao cat)
    {
        try
        {
            CatDao updatedCat = catRepository.save(cat);

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

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable Long id)
    {
        try
        {
            catRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('Owner', 'Admin')")
    @GetMapping("/id/{id}/ownerId/{ownerId}")
    public ResponseEntity<CatDao> getCat(@PathVariable Long id, @PathVariable Long ownerId)
    {
        try
        {
            CatDao cat = catRepository.findByIdAndOwnerId(id, ownerId);

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

    @PreAuthorize("hasAnyRole('Owner', 'Admin')")
    @GetMapping("/name/{name}/id/{id}")
    public ResponseEntity<List<CatDao>> getCatsByName(@PathVariable String name, @PathVariable Long id)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByNameAndOwnerId(name, id);

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

    @PreAuthorize("hasAnyRole('Owner', 'Admin')")
    @GetMapping("/breed/{breed}/id/{id}")
    public ResponseEntity<List<CatDao>> getCatsByBreed(@PathVariable String breed, @PathVariable Long id)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByBreedAndOwnerId(breed, id);

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

    @PreAuthorize("hasAnyRole('Owner', 'Admin')")
    @GetMapping("/birthDate/{birthDate}/id/{id}")
    public ResponseEntity<List<CatDao>> getCatsByBirthDate(@PathVariable LocalDate birthDate, @PathVariable Long id)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByBirthDateAndOwnerId(birthDate, id);

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

    @PreAuthorize("hasAnyRole('Owner', 'Admin')")
    @GetMapping("/color/{color}/id/{id}")
    public ResponseEntity<List<CatDao>> getCatsByColor(@PathVariable String color, @PathVariable Long id)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByColorAndOwnerId(color, id);

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

    @PreAuthorize("hasAnyRole('Owner', 'Admin')")
    @GetMapping("cats/id/{id}")
    public ResponseEntity<List<CatDao>> getAllCatsByOwner(@RequestParam Long id)
    {
        try
        {
            List<CatDao> cats = catRepository.findAllByOwnerId(id);

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
}