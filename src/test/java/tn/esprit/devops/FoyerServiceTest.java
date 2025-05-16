package tn.esprit.devops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops.dao.entities.Foyer;
import tn.esprit.devops.services.foyer.IFoyerService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FoyerServiceTest {

   @Autowired
   IFoyerService foyerService;

   @Test
   void testAjouterFoyer() {
      Foyer foyer = Foyer.builder()
              .nomFoyer("Foyer Test")
              .capaciteFoyer(50)
              .build();

      Foyer savedFoyer = foyerService.addOrUpdate(foyer);

      assertNotNull(savedFoyer);
      assertEquals("Foyer Test", savedFoyer.getNomFoyer());
      assertEquals(50, savedFoyer.getCapaciteFoyer());
      assertTrue(savedFoyer.getIdFoyer() > 0, "L'ID du foyer doit être généré automatiquement.");
   }
}
