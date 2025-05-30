package tn.esprit.devops.services.reservation;


import tn.esprit.devops.dao.entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    Reservation addOrUpdate(Reservation r);
    List<Reservation> findAll();
    Reservation findById(String id);
    void deleteById(String id);
    void delete(Reservation r);
    Reservation ajouterReservationEtAssignerAChambreEtAEtudiant (Long numChambre, long cin) ;
    long  getReservationParAnneeUniversitaire(LocalDate debutAnnee, LocalDate finAnnee ) ;
    String annulerReservation (long cinEtudiant) ;

}
