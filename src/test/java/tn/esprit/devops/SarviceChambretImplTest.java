package tn.esprit.devops;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops.dao.entities.Chambre;

import tn.esprit.devops.dao.entities.TypeChambre;
import tn.esprit.devops.services.chambre.ChambreService;



@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class SarviceChambretImplTest {
    @Autowired
    private ChambreService chambreService;
    @Test
    public void testAjouterEtudiant() {
        // Création d'un chambre
        Chambre chambre = Chambre.builder()
                .numeroChambre(10)
                .typeC(TypeChambre.SIMPLE)
                .build();

        // Ajout de l'chambre 
        Chambre savedChambre = chambreService.addOrUpdate(chambre);

        // Vérifier que l'chambre a bien été ajouté et qu'il a un ID attribué
        Assertions.assertNotNull(savedChambre);

        // Vérifier que le NumeroChambre ne dépassent pas 999
        Assertions.assertTrue(savedChambre.getNumeroChambre() < 999);

        // Nettoyer la base en supprimant l’chambre ajouté
        chambreService.delete(savedChambre);
    }
}
