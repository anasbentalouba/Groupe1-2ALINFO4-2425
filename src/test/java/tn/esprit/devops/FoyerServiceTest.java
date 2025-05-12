package tn.esprit.devops;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops.dao.entities.Foyer;
import tn.esprit.devops.services.foyer.IFoyerService;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class FoyerServiceTest {
    IFoyerService foyerService;

    @Test
    public void testAjouterFoyer(){
       Foyer foyer = Foyer.builder().nomFoyer("Foyer Test").capaciteFoyer(50).build();
        Foyer savedFoyer = foyerService.addOrUpdate(foyer);
        // Vérifie que l'objet n'est pas nul
        Assertions.assertNotNull(savedFoyer);
        // Vérifie que le nom est correct
        Assertions.assertEquals(foyer.getNomFoyer(), savedFoyer.getNomFoyer());
        // Vérifie que la capacité est correcte
        Assertions.assertEquals(foyer.getCapaciteFoyer(), savedFoyer.getCapaciteFoyer());
        assertTrue(savedFoyer.getIdFoyer() > 0, "L'ID du foyer doit être généré automatiquement.");
    }
}
