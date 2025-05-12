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

    long countChambreByTypeC(TypeChambre typeChambre);


    List<Chambre> findByBlocNomBloc(String nomB);

    @Query("select c from Chambre c where c.bloc.nomBloc=?1")
    List<Chambre> getChambresParNomBlocJPQL(String nomBloc);

    @Query(value = "SELECT c.* FROM t_chambre c JOIN t_bloc b " +
            "ON c.bloc_id_bloc=b.id_bloc WHERE b.nom_bloc=:nom"
            , nativeQuery = true)
    List<Chambre> getChambresParNomBlocSQL(@Param(value = "nom") String nomBloc);



}
