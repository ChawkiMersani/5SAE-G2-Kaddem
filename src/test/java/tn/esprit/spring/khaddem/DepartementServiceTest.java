package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DepartementServiceTest {

    @Autowired
    private DepartementServiceImpl departementService;

    @MockBean
    private DepartementRepository departementRepository;

    @MockBean
    private UniversiteRepository universiteRepository;

    private Departement departement1;
    private Departement departement2;

    @BeforeEach
    public void setup() {

        departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Informatique");

        departement2 = new Departement();
        departement2.setIdDepartement(2);
        departement2.setNomDepart("Mathématiques");

        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> {
            Departement saved = invocation.getArgument(0);
            if (saved.getIdDepartement() == null) {
                saved.setIdDepartement(new Random().nextInt());
            }
            return saved;
        });

        when(departementRepository.findAll()).thenReturn(Arrays.asList(departement1, departement2));
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement1));
        when(departementRepository.findById(2)).thenReturn(Optional.of(departement2));
    }

    @Test
    public void testAddAndRetrieveDepartement() {
        Departement savedDepartement1 = departementService.addDepartement(departement1);
        Departement savedDepartement2 = departementService.addDepartement(departement2);

        assertNotNull(savedDepartement1.getIdDepartement());
        assertNotNull(savedDepartement2.getIdDepartement());

        List<Departement> departements = departementService.retrieveAllDepartements();
        assertEquals(2, departements.size());
        assertTrue(departements.stream().anyMatch(d -> d.getNomDepart().equals("Informatique")));
        assertTrue(departements.stream().anyMatch(d -> d.getNomDepart().equals("Mathématiques")));
    }

    @Test
    public void testRetrieveDepartementById() {
        Departement retrievedDepartement = departementService.retrieveDepartement(1);
        assertEquals("Informatique", retrievedDepartement.getNomDepart());

        retrievedDepartement = departementService.retrieveDepartement(2);
        assertEquals("Mathématiques", retrievedDepartement.getNomDepart());
    }
}
