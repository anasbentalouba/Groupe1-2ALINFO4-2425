package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.devops.dao.entities.Foyer;


public interface FoyerRepository extends JpaRepository<Foyer,Long> {
    Foyer findByNomFoyer(String nom);



}

