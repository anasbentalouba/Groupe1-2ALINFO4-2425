package tn.esprit.devops;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops.dao.entities.Chambre;
import tn.esprit.devops.dao.entities.TypeChambre;
import tn.esprit.devops.dao.repositories.ChambreRepository;
import tn.esprit.devops.services.chambre.ChambreService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreService chambreService;

    @Test
     void testAjouterChambre() {
        // GIVEN
        Chambre chambre = Chambre.builder()
                .numeroChambre(10)
                .typeC(TypeChambre.SIMPLE)
                .build();

        Chambre savedChambre = Chambre.builder()
                .idChambre(1L)
                .numeroChambre(10)
                .typeC(TypeChambre.SIMPLE)
                .build();

        when(chambreRepository.save(chambre)).thenReturn(savedChambre);

        // WHEN
        Chambre result = chambreService.addOrUpdate(chambre);

        // THEN
        Assertions.assertNotNull(result);
        Assertions.assertEquals(10, result.getNumeroChambre());
        Assertions.assertTrue(result.getNumeroChambre() < 999);

        verify(chambreRepository, times(1)).save(chambre);
    }
}
