package tn.esprit.devops.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops.dao.entities.Universite;
import tn.esprit.devops.dao.repositories.UniversiteRepository;
import tn.esprit.devops.services.universite.UniversiteService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite universite;

    @BeforeEach
    void setUp() {
        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Université de Test");
        universite.setAdresse("Adresse Test");
    }

    @Test
    void testAddOrUpdate() {

        when(universiteRepository.save(universite)).thenReturn(universite);


        Universite savedUniversite = universiteService.addOrUpdate(universite);


        assertNotNull(savedUniversite);
        assertEquals("Université de Test", savedUniversite.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }
}
