package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.devops.dao.entities.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {


    @Query("select e from Etudiant e where e.nomEt=?1")
    List<Etudiant> selectJPQL(String nom);

    Etudiant getByNomEtAndPrenomEt(String nom, String prenom);

    Etudiant findByCin(long cin);



}



