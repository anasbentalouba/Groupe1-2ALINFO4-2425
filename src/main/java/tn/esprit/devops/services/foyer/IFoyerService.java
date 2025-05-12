package tn.esprit.devops.services.foyer;

import tn.esprit.devops.dao.entities.Foyer;
import tn.esprit.devops.dao.entities.Universite;

import java.util.List;

public interface IFoyerService {
    Foyer addOrUpdate(Foyer f);

    List<Foyer> findAll();

    Foyer findById(long id);

    void deleteById(long id);

    void delete(Foyer f);

    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);


    Universite desaffecterFoyerAUniversite(long idUniversite);

    Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, long idUniversite); // Universite: Parent / Foyer:Child

    Universite affecterFoyerAUniversite(long idF, long idU);

}
