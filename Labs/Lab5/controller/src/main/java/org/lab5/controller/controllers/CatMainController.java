package org.lab5.controller.controllers;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.lab5.controller.creators.CatCreate;
import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.dto.CatDto;
import org.lab5.dataAccess.dto.CatList;
import org.lab5.dataAccess.dto.FilterDto;
import org.lab5.dataAccess.dto.FriendsDto;
import org.lab5.dataAccess.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cats")
public class CatMainController
{
    private final CatRepository catRepository;
    private final KafkaTemplate<String, CatCreate> createCat;
    private final KafkaTemplate<String, Integer> getCat;
    private final KafkaTemplate<String, FriendsDto> friends;
    private final KafkaTemplate<String, FilterDto> filters;
    private final Consumer<String, CatList> consumerCats;
    private final Consumer<String, CatList> consumerFriends;
    private final Consumer<String, CatList> consumerFiltered;
    private final Consumer<String, CatDto> consumer;

    @Autowired
    public CatMainController(CatRepository catRepository,
                             KafkaTemplate<String, CatCreate> createCat,
                             KafkaTemplate<String, Integer> getCat,
                             KafkaTemplate<String, FriendsDto> friends,
                             KafkaTemplate<String, FilterDto> filters,
                             @Qualifier("CKF") Consumer<String, CatList> consumerCats,
                             @Qualifier("CKFF") Consumer<String, CatList> consumerFriends,
                             @Qualifier("CFF") Consumer<String, CatList> consumerFiltered,
                             Consumer<String, CatDto> consumer) {
        this.catRepository = catRepository;
        this.createCat = createCat;
        this.getCat = getCat;
        this.friends = friends;
        this.filters = filters;
        this.consumerCats = consumerCats;
        this.consumerFriends = consumerFriends;
        this.consumerFiltered = consumerFiltered;
        this.consumer = consumer;
    }


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("{id}")
    public CatDto getCatById(@PathVariable int id) throws InterruptedException {
        getCat.send("get_by_id_cat", id);
        TimeUnit.MILLISECONDS.sleep(1000);
        ConsumerRecords<String, CatDto> consumerRecords = consumer.poll(Duration.ofMillis(2000));
        Iterator<ConsumerRecord<String, CatDto>> iterator = consumerRecords.iterator();
        if (iterator.hasNext()) {
            CatDto value = iterator.next().value();
            consumer.commitSync();
            return value;
        } else {
            return getCatById(id);
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