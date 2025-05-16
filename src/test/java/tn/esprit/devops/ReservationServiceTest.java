package tn.esprit.devops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops.dao.entities.*;
import tn.esprit.devops.dao.repositories.*;
import tn.esprit.devops.services.reservation.ReservationService;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationService reservationService;

    @Test
    void testAjouterEtSupprimerReservationSansChambre() {
        // --- Given ---
        Reservation reservation = Reservation.builder()
                .idReservation("R1")
                .anneeUniversitaire(LocalDate.of(2025, 9, 1))
                .estValide(true)
                .etudiants(new ArrayList<>())
                .build();

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        // --- When: Ajouter ---
        Reservation saved = reservationService.ajouterReservationSansChambre(reservation);

        // --- Then: Check save result ---
        assertNotNull(saved);
        assertEquals("R1", saved.getIdReservation());
        verify(reservationRepository).save(reservation);

        // --- When: Delete ---
        doNothing().when(reservationRepository).deleteById("R1");
        reservationService.supprimerReservationParId("R1");

        // --- Then: Verify delete ---
        verify(reservationRepository).deleteById("R1");
    }
}
