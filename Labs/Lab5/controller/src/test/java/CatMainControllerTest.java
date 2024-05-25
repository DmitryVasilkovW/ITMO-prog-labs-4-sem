import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lab5.controller.controllers.CatMainController;
import org.lab5.dataAccess.dao.CatDao;
import org.lab5.dataAccess.repositories.CatRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatMainControllerTest
{
    @Mock
    private CatRepository catRepository;

    @InjectMocks
    private CatMainController catMainController;

    private CatDao catDao;

    @BeforeEach
    void setUp()
    {
        catDao = new CatDao();
        catDao.setId(1);
        catDao.setName("Tom");
        catDao.setBreed("Siamese");
        catDao.setBirthDate(LocalDate.of(2020, 1, 1));
        catDao.setColor("Black");
    }

    @Test
    void testGetCatsByName()
    {
        when(catRepository.findAllByName("Tom")).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByName("Tom");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetCatsByBreed()
    {
        when(catRepository.findAllByBreed("Siamese")).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByBreed("Siamese");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetCatsByBirthDate()
    {
        when(catRepository.findAllByBirthDate(LocalDate.of(2020, 1, 1))).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByBirthDate(LocalDate.of(2020, 1, 1));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetCatsByColor()
    {
        when(catRepository.findAllByColor("Black")).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByColor("Black");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testCreateCat()
    {
        when(catRepository.save(catDao)).thenReturn(catDao);

        ResponseEntity<CatDao> response = catMainController.createCat(catDao);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catDao, response.getBody());
    }

    @Test
    void testUpdateCat()
    {
        when(catRepository.save(catDao)).thenReturn(catDao);

        ResponseEntity<CatDao> response = catMainController.updateCat(1, catDao);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catDao, response.getBody());
    }

    @Test
    void testDeleteCat()
    {
        ResponseEntity<Void> response = catMainController.deleteCat(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetCatWithOwner()
    {
        when(catRepository.findByIdAndOwnerId(1L, 1L)).thenReturn(catDao);

        ResponseEntity<CatDao> response = catMainController.getCat(1L, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(catDao, response.getBody());
    }

    @Test
    void testGetCatsByNameAndOwner()
    {
        when(catRepository.findAllByNameAndOwnerId("Tom", 1L)).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByName("Tom", 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetCatsByBreedAndOwner()
    {
        when(catRepository.findAllByBreedAndOwnerId("Siamese", 1L)).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByBreed("Siamese", 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetCatsByBirthDateAndOwner() {
        when(catRepository.findAllByBirthDateAndOwnerId(LocalDate.of(2020, 1, 1), 1L)).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByBirthDate(LocalDate.of(2020, 1, 1), 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetCatsByColorAndOwner()
    {
        when(catRepository.findAllByColorAndOwnerId("Black", 1L)).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getCatsByColor("Black", 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }

    @Test
    void testGetAllCatsByOwner()
    {
        when(catRepository.findAllByOwnerId(1L)).thenReturn(Collections.singletonList(catDao));

        ResponseEntity<List<CatDao>> response = catMainController.getAllCatsByOwner(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(catDao, response.getBody().get(0));
    }
}