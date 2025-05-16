package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.devops.dao.entities.Reservation;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    int countByAnneeUniversitaireBetween(LocalDate dateInf, LocalDate dateSup);
    Reservation findByEtudiantsCinAndEstValide(long cin,boolean isValid);

}

