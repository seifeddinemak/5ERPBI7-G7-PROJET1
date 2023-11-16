
package tn.esprit.spring;

import antlr.collections.List;
import org.assertj.core.util.Arrays;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.SkierServicesImpl;
import tn.esprit.spring.entities.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import java.util.List


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SkierServicesImplTest {
    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SkierServicesImpl skierService;

    @Test
    public void testRemoveSkier() {
        Long numSkier = 1L;
        skierService.removeSkier(numSkier);
        verify(skierRepository, times(1)).deleteById(numSkier);
    }

    @Test
    public void testRetrieveSkier() {
        Long numSkier = 1L;
        Skier skier = new Skier();
        when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));

        Skier result = skierService.retrieveSkier(numSkier);
        assertNotNull(result);
        assertEquals(skier, result);
    }

    @Test
    public void testRetrieveAllSkiers() {
        List<Skier> skiers = Arrays.asList(new Skier(), new Skier());
        when(skierRepository.findAll()).thenReturn(skiers);

        List<Skier> result = skierService.retrieveAllSkiers();
        assertNotNull(result);
        assertEquals(skiers.size(), result.size());
    }

    @Test
    public void testAddSkier() {
        Skier skierToAdd = new Skier();
        when(skierRepository.save(skierToAdd)).thenReturn(skierToAdd);

        Skier result = skierService.addSkier(skierToAdd);
        assertNotNull(result);
        assertEquals(skierToAdd, result);
    }
}

