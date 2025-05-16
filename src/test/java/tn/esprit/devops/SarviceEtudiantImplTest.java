package tn.esprit.devops;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.devops.dao.entities.Etudiant;
import tn.esprit.devops.services.etudiant.EtudiantService;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class SarviceEtudiantImplTest {
    @Autowired
    private EtudiantService etudiantService;

    @Test
    public void testAjouterEtudiant() {
        // Création d'un étudiant avec une date de naissance qui permet de tester la logique liée à l'âge si besoin
        Etudiant etudiant = Etudiant.builder()
                .nomEt("BenAli")
                .prenomEt("Sami")
                .cin(12345678L)
                .ecole("ESPRIT")
                .dateNaissance(LocalDate.parse("2000-06-15"))
                .build();

        // Ajout de l'étudiant
        Etudiant savedEtudiant = etudiantService.addOrUpdate(etudiant);

        // Vérifier que l'étudiant a bien été ajouté et qu'il a un ID attribué
        Assertions.assertNotNull(savedEtudiant.getIdEtudiant());

        // Vérifier que le nom et le prénom ne dépassent pas une certaine longueur
        Assertions.assertTrue(savedEtudiant.getNomEt().length() < 20);
        Assertions.assertTrue(savedEtudiant.getPrenomEt().length() < 20);

        // Vérifier que la CIN est un entier à 8 chiffres
        Assertions.assertTrue(String.valueOf(savedEtudiant.getCin()).length() == 8);

        // Vérifier que l'école est bien enregistrée
        Assertions.assertEquals("ESPRIT", savedEtudiant.getEcole());

        // Nettoyer la base en supprimant l’étudiant ajouté
        etudiantService.delete(savedEtudiant);
    }

}
