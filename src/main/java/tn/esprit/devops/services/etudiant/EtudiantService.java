package tn.esprit.devops.services.etudiant;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.devops.dao.entities.Etudiant;
import tn.esprit.devops.dao.repositories.EtudiantRepository;
import tn.esprit.devops.dao.repositories.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantService implements IEtudiantService {
    EtudiantRepository repo;
    ReservationRepository reservationRepository;

    @Override
    public Etudiant addOrUpdate(Etudiant e) {
        return repo.save(e);
    }

    @Override
    public List<Etudiant> findAll() {
        return repo.findAll();
    }

    @Override
    public Etudiant findById(long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(Etudiant e) {
        repo.delete(e);
    }

    @Override
    public List<Etudiant> selectJPQL(String nom) {
        return repo.selectJPQL(nom);
    }

}
