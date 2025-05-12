package tn.esprit.devops;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops.dao.entities.Foyer;
import tn.esprit.devops.dao.repositories.FoyerRepository;
import tn.esprit.devops.services.foyer.FoyerService;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FoyerServiceMockitoTest {
FoyerRepository foyerRepository;
FoyerService foyerService;
    @Test
    public void testAjouterFoyer(){
        Foyer foyer = Foyer.builder().nomFoyer("Foyer Test").capaciteFoyer(50).build();
        Mockito.when(foyerRepository.save(Mockito.any(Foyer.class))).thenReturn(foyer);
        Foyer savedFoyer=foyerService.addOrUpdate(foyer);
        Assertions.assertEquals(foyer.getNomFoyer(), savedFoyer.getNomFoyer());
        Assertions.assertEquals(foyer.getCapaciteFoyer(), savedFoyer.getCapaciteFoyer());
        verify(foyerRepository).save(Mockito.any(Foyer.class));
    }
}
