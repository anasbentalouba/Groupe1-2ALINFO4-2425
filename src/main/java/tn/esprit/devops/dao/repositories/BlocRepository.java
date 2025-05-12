package tn.esprit.devops.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.devops.dao.entities.Bloc;


public interface BlocRepository extends JpaRepository<Bloc, Long> {
    Bloc findByNomBloc(String nom);

}
