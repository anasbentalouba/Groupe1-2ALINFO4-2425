package tn.esprit.DevOps.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DevOps.DAO.Entities.Universite;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {
     Universite findByNomUniversite(String nomUniversite);
    // Afficher la liste des universités qui ont des étudiants dont leurs noms contiennet
    // la chaine de caractère en paramètre et leurs dates de naissance entre deux dates
    // passées en paramètre
    List<Universite> findByFoyerBlocsChambresReservationsEtudiantsNomEtLikeAndFoyerBlocsChambresReservationsEtudiantsDateNaissanceBetween(String nom, LocalDate date1, LocalDate date2);
    List<Universite> findByFoyerCapaciteFoyerLessThan(long u);
}
