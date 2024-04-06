import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.CatService;
import controllers.CatController;
import models.Cat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatControllerTest
{
    @Test
    public void testGetCatById()
    {
        CatService catService = Mockito.mock(CatService.class);
        Cat cat = new Cat();
        when(catService.getCatById(1)).thenReturn(cat);

        CatController catController = new CatController(catService);
        Cat result = catController.getCatById(1);
        assertEquals(cat, result);

        verify(catService, times(1)).getCatById(1);
    }

    @Test
    public void testGetCatsByName()
    {
        CatService catService = Mockito.mock(CatService.class);
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catService.getCatsByName("Test")).thenReturn(cats);

        CatController catController = new CatController(catService);
        List<Cat> result = catController.getCatsByName("Test");
        assertEquals(cats, result);

        verify(catService, times(1)).getCatsByName("Test");
    }

    @Test
    public void testGetCatsByBirthDate()
    {
        CatService catService = Mockito.mock(CatService.class);
        LocalDate birthDate = LocalDate.now();
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catService.getCatsByBirthDate(birthDate)).thenReturn(cats);

        CatController catController = new CatController(catService);
        List<Cat> result = catController.getCatsByBirthDate(birthDate);
        assertEquals(cats, result);

        verify(catService, times(1)).getCatsByBirthDate(birthDate);
    }

    @Test
    public void testGetCatsByBreed()
    {
        CatService catService = Mockito.mock(CatService.class);
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catService.getCatsByBreed("Test")).thenReturn(cats);

        CatController catController = new CatController(catService);
        List<Cat> result = catController.getCatsByBreed("Test");
        assertEquals(cats, result);

        verify(catService, times(1)).getCatsByBreed("Test");
    }

    @Test
    public void testGetCatsByColor()
    {
        CatService catService = Mockito.mock(CatService.class);
        List<Cat> cats = Arrays.asList(new Cat(), new Cat());
        when(catService.getCatsByColor("Test")).thenReturn(cats);

        CatController catController = new CatController(catService);
        List<Cat> result = catController.getCatsByColor("Test");
        assertEquals(cats, result);

        verify(catService, times(1)).getCatsByColor("Test");
    }

    @Test
    public void testUpdateCatName()
    {
        CatService catService = Mockito.mock(CatService.class);
        CatController catController = new CatController(catService);

        catController.updateCatName(1, "Test");
        verify(catService, times(1)).updateCatName(1, "Test");
    }

    @Test
    public void testUpdateCatBirthDate()
    {
        CatService catService = Mockito.mock(CatService.class);
        LocalDate birthDate = LocalDate.now();
        CatController catController = new CatController(catService);

        catController.updateCatBirthDate(1, birthDate);
        verify(catService, times(1)).updateCatBirthDate(1, birthDate);
    }

    @Test
    public void testUpdateCatBreed()
    {
        CatService catService = Mockito.mock(CatService.class);
        CatController catController = new CatController(catService);

        catController.updateCatBreed(1, "Test");
        verify(catService, times(1)).updateCatBreed(1, "Test");
    }

    @Test
    public void testUpdateCatColor()
    {
        CatService catService = Mockito.mock(CatService.class);
        CatController catController = new CatController(catService);

        catController.updateCatColor(1, "Test");
        verify(catService, times(1)).updateCatColor(1, "Test");
    }

    @Test
    public void testDeleteCat()
    {
        CatService catService = Mockito.mock(CatService.class);
        CatController catController = new CatController(catService);

        catController.deleteCat(1);
        verify(catService, times(1)).deleteCat(1);
    }
}
