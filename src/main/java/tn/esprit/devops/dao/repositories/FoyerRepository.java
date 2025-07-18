package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.devops.dao.entities.Foyer;

import java.util.List;


public interface FoyerRepository extends JpaRepository<Foyer,Long> {
    Foyer findByNomFoyer(String nom);

    @Query("SELECT f FROM Foyer f")
    List<Foyer> findAllFoyers();

}

