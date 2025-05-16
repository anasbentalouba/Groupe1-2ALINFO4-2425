package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.devops.dao.entities.Chambre;
import tn.esprit.devops.dao.entities.TypeChambre;


import java.time.LocalDate;
import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    Chambre findByNumeroChambre(long num);




    //Keyword
    int countReservationsByIdChambreAndReservationsAnneeUniversitaireBetween(long chambreId, LocalDate dateDebutAU, LocalDate dateFinAU);

    //*****************************************************************
    Chambre findByReservationsIdReservation(String idReservation);

    long count();




    List<Chambre> findByBlocNomBloc(String nomB);





}
