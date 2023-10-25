package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void retrieveAllDepartementsTest() {
        // Given
        Departement dept1 = new Departement();
        Departement dept2 = new Departement();
        when(departementRepository.findAll()).thenReturn(Arrays.asList(dept1, dept2));

        // When
        List<Departement> result = departementService.retrieveAllDepartements();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    public void addDepartementTest() {
        // Given
        Departement dept = new Departement();
        when(departementRepository.save(dept)).thenReturn(dept);

        // When
        Departement result = departementService.addDepartement(dept);

        // Then
        assertEquals(dept, result);
    }

    @Test
    public void updateDepartementTest() {
        // Given
        Departement dept = new Departement();
        when(departementRepository.save(dept)).thenReturn(dept);

        // When
        Departement result = departementService.updateDepartement(dept);

        // Then
        assertEquals(dept, result);
    }

    @Test
    public void retrieveDepartementTest() {
        // Given
        Integer id = 1;
        Departement dept = new Departement();
        when(departementRepository.findById(id)).thenReturn(Optional.of(dept));

        // When
        Departement result = departementService.retrieveDepartement(id);

        // Then
        assertEquals(dept, result);
    }

    @Test
    public void retrieveDepartementsByUniversiteTest() {
        // Given
        Integer id = 1;
        Universite universite = new Universite();
        Departement dept1 = new Departement();
        Departement dept2 = new Departement();
        universite.setDepartements(Arrays.asList(dept1, dept2));
        when(universiteRepository.findById(id)).thenReturn(Optional.of(universite));

        // When
        List<Departement> result = departementService.retrieveDepartementsByUniversite(id);

        // Then
        assertEquals(2, result.size());
        assertEquals(Arrays.asList(dept1, dept2), result);
    }
}
