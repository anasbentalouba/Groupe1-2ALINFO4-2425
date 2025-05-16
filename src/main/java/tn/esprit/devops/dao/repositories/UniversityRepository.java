package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devops.dao.entities.Universite;

public interface UniversityRepository extends JpaRepository<Universite, Long> {
    Universite findByNomUniversite(String nomUniversite);
}
