package tn.esprit.devops.services.foyer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.devops.dao.entities.Bloc;
import tn.esprit.devops.dao.entities.Foyer;
import tn.esprit.devops.dao.entities.Universite;
import tn.esprit.devops.dao.repositories.BlocRepository;
import tn.esprit.devops.dao.repositories.FoyerRepository;
import tn.esprit.devops.dao.repositories.UniversityRepository;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerService implements IFoyerService {
    private final FoyerRepository foyerRepository;
    FoyerRepository repo;
    UniversityRepository universiteRepository;
    BlocRepository blocRepository;

    @Override
    public Foyer addOrUpdate(Foyer f) {
        return repo.save(f);
    }

    @Override
    public List<Foyer> findAll() {
        return repo.findAll();
    }

    @Override
    public Foyer findById(long id) {
        return repo.findById(id).orElse(null);
    }


    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(Foyer f) {
        repo.delete(f);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer f = findById(idFoyer); // Child
        Universite u = universiteRepository.findByNomUniversite(nomUniversite); // Parent
        u.setFoyer(f);
        return universiteRepository.save(u);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        List<Bloc> blocs = foyer.getBlocs();
        Foyer f = repo.save(foyer);

        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        if (u == null) {
            return null;
        }

        for (Bloc bloc : blocs) {
            bloc.setFoyer(f);
            blocRepository.save(bloc);
        }
        u.setFoyer(f);
        return universiteRepository.save(u).getFoyer();
    }

    @Override
    public Universite affecterFoyerAUniversite(long idF, long idU) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idU);
        Optional<Foyer> optionalFoyer = foyerRepository.findById(idF);

        if (optionalUniversite.isPresent() && optionalFoyer.isPresent()) {
            Universite u = optionalUniversite.get();
            Foyer f = optionalFoyer.get();
            u.setFoyer(f);
            return universiteRepository.save(u);
        }

        return null;
    }


    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);

        if (optionalUniversite.isPresent()) {
            Universite u = optionalUniversite.get();
            u.setFoyer(null);
            return universiteRepository.save(u);
        }

        return null;
    }


}

