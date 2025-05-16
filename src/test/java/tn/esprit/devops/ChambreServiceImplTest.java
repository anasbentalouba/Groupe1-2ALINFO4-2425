package tn.esprit.devops;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops.dao.entities.Chambre;
import tn.esprit.devops.dao.entities.TypeChambre;
import tn.esprit.devops.dao.repositories.ChambreRepository;
import tn.esprit.devops.services.chambre.ChambreService;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreService chambreService;

    @Test
    @Order(1)
     void testAddOrUpdateChambre() {
        // Arrange
        Chambre chambre = Chambre.builder()
                .numeroChambre(10)
                .typeC(TypeChambre.SIMPLE)
                .build();

        // Simuler que le repo retourne la même chambre avec un ID fictif
        Chambre savedChambre = Chambre.builder()
                .idChambre(1L)
                .numeroChambre(10)
                .typeC(TypeChambre.SIMPLE)
                .build();

        when(chambreRepository.save(any(Chambre.class))).thenReturn(savedChambre);

        // Act
        Chambre result = chambreService.addOrUpdate(chambre);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(10, result.getNumeroChambre());
        Assertions.assertTrue(result.getNumeroChambre() < 999);

        verify(chambreRepository, times(1)).save(any(Chambre.class));
    }
}
