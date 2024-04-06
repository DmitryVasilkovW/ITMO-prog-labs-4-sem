import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import services.OwnerService;
import controllers.OwnerController;
import models.Owner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OwnerControllerTest
{
    @Test
    public void testGetOwnerById()
    {
        OwnerService ownerService = Mockito.mock(OwnerService.class);
        Owner owner = new Owner();
        when(ownerService.getOwnerById(1)).thenReturn(owner);

        OwnerController ownerController = new OwnerController(ownerService);
        Owner result = ownerController.getOwnerById(1);
        assertEquals(owner, result);

        verify(ownerService, times(1)).getOwnerById(1);
    }

    @Test
    public void testGetOwnersByName()
    {
        OwnerService ownerService = Mockito.mock(OwnerService.class);
        List<Owner> owners = Arrays.asList(new Owner(), new Owner());
        when(ownerService.getOwnersByName("Test")).thenReturn(owners);

        OwnerController ownerController = new OwnerController(ownerService);
        List<Owner> result = ownerController.getOwnersByName("Test");
        assertEquals(owners, result);

        verify(ownerService, times(1)).getOwnersByName("Test");
    }

    @Test
    public void testGetOwnersByBirthDate()
    {
        OwnerService ownerService = Mockito.mock(OwnerService.class);
        LocalDate birthDate = LocalDate.now();
        List<Owner> owners = Arrays.asList(new Owner(), new Owner());
        when(ownerService.getOwnersByBirthDate(birthDate)).thenReturn(owners);

        OwnerController ownerController = new OwnerController(ownerService);
        List<Owner> result = ownerController.getOwnersByBirthDate(birthDate);
        assertEquals(owners, result);

        verify(ownerService, times(1)).getOwnersByBirthDate(birthDate);
    }

    @Test
    public void testUpdateOwnerName()
    {
        OwnerService ownerService = Mockito.mock(OwnerService.class);
        OwnerController ownerController = new OwnerController(ownerService);

        ownerController.updateOwnerName(1, "Test");
        verify(ownerService, times(1)).updateOwnerName(1, "Test");
    }

    @Test
    public void testUpdateOwnerBirthDate()
    {
        OwnerService ownerService = Mockito.mock(OwnerService.class);
        LocalDate birthDate = LocalDate.now();
        OwnerController ownerController = new OwnerController(ownerService);

        ownerController.updateOwnerBirthDate(1, birthDate);
        verify(ownerService, times(1)).updateOwnerBirthDate(1, birthDate);
    }

    @Test
    public void testDeleteOwner()
    {
        OwnerService ownerService = Mockito.mock(OwnerService.class);
        OwnerController ownerController = new OwnerController(ownerService);

        ownerController.deleteOwner(1);
        verify(ownerService, times(1)).deleteOwner(1);
    }
}
