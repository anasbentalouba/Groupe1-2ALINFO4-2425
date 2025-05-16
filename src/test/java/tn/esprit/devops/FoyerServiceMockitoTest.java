package tn.esprit.devops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops.dao.entities.Foyer;
import tn.esprit.devops.dao.repositories.FoyerRepository;
import tn.esprit.devops.services.foyer.FoyerService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoyerServiceMockitoTest {

    @Mock
    FoyerRepository foyerRepository;

    @InjectMocks
    FoyerService foyerService;

    @Test
    void testAjouterFoyer() {
        Foyer foyer = Foyer.builder().nomFoyer("Foyer Test").capaciteFoyer(50).build();

        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        Foyer savedFoyer = foyerService.addOrUpdate(foyer);

        assertEquals("Foyer Test", savedFoyer.getNomFoyer());
        assertEquals(50, savedFoyer.getCapaciteFoyer());
        verify(foyerRepository).save(any(Foyer.class));
    }
}
