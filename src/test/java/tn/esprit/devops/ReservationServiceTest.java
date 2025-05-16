package tn.esprit.devops;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops.dao.entities.*;
import tn.esprit.devops.dao.repositories.*;
import tn.esprit.devops.services.reservation.ReservationService;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationServiceTest {

   @Mock
   ReservationRepository reservationRepository;

   @Mock
   ChambreRepository chambreRepository;

   @Mock
   EtudiantRepository etudiantRepository;

   @InjectMocks
   ReservationService reservationService;

   @Test
   @Order(1)
   void testAjouterReservationEtAssignerAChambreEtAEtudiant_Success() {
      Long numeroChambre = 101L;
      long cin = 12345678L;

      Chambre chambre = new Chambre();
      chambre.setIdChambre(1L);
      chambre.setNumeroChambre(numeroChambre);
      chambre.setTypeC(TypeChambre.DOUBLE);
      Bloc bloc = new Bloc();
      bloc.setNomBloc("B1");
      chambre.setBloc(bloc);
      chambre.setReservations(new ArrayList<>());

      Etudiant etudiant = new Etudiant();
      etudiant.setCin(cin);

      when(chambreRepository.findByNumeroChambre(numeroChambre)).thenReturn(chambre);
      when(etudiantRepository.findByCin(cin)).thenReturn(etudiant);
      when(chambreRepository.countReservationsByIdChambreAndReservationsAnneeUniversitaireBetween(
              anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(1);

      when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> {
         Reservation r = invocation.getArgument(0);
         if (r.getEtudiants() == null) {
            r.setEtudiants(new ArrayList<>());
         }
         return r;
      });

      when(chambreRepository.save(any(Chambre.class))).thenAnswer(i -> i.getArgument(0));

      Reservation result = reservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(numeroChambre, cin);

      assertNotNull(result);
      assertTrue(result.isEstValide());
      assertEquals(1, result.getEtudiants().size());
      assertEquals(cin, result.getEtudiants().get(0).getCin());
      verify(reservationRepository).save(any(Reservation.class));
      verify(chambreRepository).save(any(Chambre.class));
   }

   @Test
   @Order(2)
   void testAjouterReservationEtAssignerAChambreEtAEtudiant_ChambrePleine() {
      Long numeroChambre = 102L;
      long cin = 87654321L;

      Chambre chambre = new Chambre();
      chambre.setIdChambre(2L);
      chambre.setNumeroChambre(numeroChambre);
      chambre.setTypeC(TypeChambre.SIMPLE);
      Bloc bloc = new Bloc();
      bloc.setNomBloc("B2");
      chambre.setBloc(bloc);

      Etudiant etudiant = new Etudiant();
      etudiant.setCin(cin);

      when(chambreRepository.findByNumeroChambre(numeroChambre)).thenReturn(chambre);
      when(etudiantRepository.findByCin(cin)).thenReturn(etudiant);
      when(chambreRepository.countReservationsByIdChambreAndReservationsAnneeUniversitaireBetween(
              anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(1);

      Reservation result = reservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(numeroChambre, cin);

      assertNull(result);
      verify(reservationRepository, never()).save(any(Reservation.class));
      verify(chambreRepository, never()).save(any(Chambre.class));
   }

   @Test
   @Order(3)
   void testAnnulerReservation() {
      long cinEtudiant = 12345678L;

      Reservation reservation = new Reservation();
      reservation.setIdReservation("2024/2025-B1-101-12345678");
      reservation.setEstValide(true);
      reservation.setEtudiants(new ArrayList<>());

      Chambre chambre = new Chambre();
      chambre.setReservations(new ArrayList<>(List.of(reservation)));

      when(reservationRepository.findByEtudiantsCinAndEstValide(cinEtudiant, true)).thenReturn(reservation);
      when(chambreRepository.findByReservationsIdReservation(reservation.getIdReservation())).thenReturn(chambre);

      String result = reservationService.annulerReservation(cinEtudiant);

      assertEquals("La réservation 2024/2025-B1-101-12345678 est annulée avec succés", result);
      verify(reservationRepository).delete(reservation);
      verify(chambreRepository).save(chambre);
   }
}
