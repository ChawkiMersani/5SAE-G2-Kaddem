package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    @InjectMocks
    DepartementServiceImpl departementService;

    @Mock
    DepartementRepository departementRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Departement departement1 = new Departement();
        departement1.setIdDepartement(1);
        departement1.setNomDepart("Informatique");

        Departement departement2 = new Departement();
        departement2.setIdDepartement(2);
        departement2.setNomDepart("Mathématiques");

        // Mock behavior for the repository
        when(departementRepository.findAll()).thenReturn(Arrays.asList(departement1, departement2));
        when(departementRepository.save(any(Departement.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testRetrieveAllDepartements() {
        List<Departement> departements = departementService.retrieveAllDepartements();

        assertEquals(2, departements.size());
        assertTrue(departements.stream().anyMatch(d -> d.getNomDepart().equals("Informatique")));
        assertTrue(departements.stream().anyMatch(d -> d.getNomDepart().equals("Mathématiques")));
    }

    @Test
    void testAddDepartement() {
        Departement newDepartement = new Departement();
        newDepartement.setNomDepart("Physics");

        Departement savedDepartement = departementService.addDepartement(newDepartement);

        assertNotNull(savedDepartement);
        assertEquals("Physics", savedDepartement.getNomDepart());

        // Verify that the save method was called once
        verify(departementRepository, times(1)).save(newDepartement);
    }
}
