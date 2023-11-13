package tn.esprit.spring.services;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.GestionStationSkiApplication;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PisteServicesImplTest {
    @Mock
    IPisteRepository pisteRepository;
    @InjectMocks
    PisteServicesImpl pisteServices;
    Piste piste= new Piste(1L,"menzah",Color.GREEN,40,12,new HashSet<Skier>());
    List<Piste> pisteList = new ArrayList<Piste>() {
        {
            add(new Piste(1L,"menzah",Color.GREEN,40,12,new HashSet<Skier>()));
            add(new Piste(2L,"aouina",Color.BLACK,50,32,new HashSet<Skier>()));
        }
    };

    @Test
    void retrieveAllPistes() {
        System.out.println("In the function");
        // Mock the behavior of the repository
        when(pisteRepository.findAll()).thenReturn(pisteList);

        // Call the method being tested
        System.out.println("Before calling retrieveAllPistes()");
        List<Piste> listpiste = pisteServices.retrieveAllPistes();

        // Assert the result
        assertNotNull(listpiste);
    }



    @Test
    void removePiste() {
        pisteServices.removePiste(2L);
        Mockito.verify(pisteRepository, times(1))
                .deleteById(2L);
    }

    @Test
    void retrievePiste() {
        System.out.println("In the function");
        // Mock the behavior of the repository
        when(pisteRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(piste));

        // Call the method being tested
        System.out.println("Before calling retrievePiste()");
        Piste pi = pisteServices.retrievePiste(2L);
        System.out.println("After calling retrievePiste() =>"+pi.getNamePiste());

        // Assert the result
        assertNotNull(pi);
    }
}
@SpringBootTest(classes = {GestionStationSkiApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
class PisteServicesImplJunitTest
{
    @Autowired
    IPisteServices pisteServices;

    Piste piste = new Piste(1L,"menzah",Color.GREEN,40,12,new HashSet<Skier>());

    @Test
    @Order(1)
    void testAddPiste() {
        Piste piste1 = pisteServices.addPiste(new Piste(1L,"menzah",Color.GREEN,40,12,new HashSet<Skier>()));
        Assertions.assertEquals("menzah", piste1.getNamePiste());
    }
    @Test
    @Order(2)
    void testRetrievePiste() {
        Piste piste1 = pisteServices.retrievePiste(1L);
        Assertions.assertNotNull(piste1);
    }
    @Test
    @Order(3)
    void testRetrieveAllPiste() {
        List<Piste> pisteList = pisteServices.retrieveAllPistes();
        Assertions.assertNotNull(pisteList);
    }
}