package tn.esprit.DevOps.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DevOps.DAO.Entities.Universite;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    Universite findByNomUniversite(String nomUniversite);
}
