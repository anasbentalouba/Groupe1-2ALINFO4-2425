package tn.esprit.DevOps.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.DevOps.DAO.Entities.Bloc;
import tn.esprit.DevOps.DAO.Entities.Chambre;

public interface BlocRepository extends JpaRepository<Bloc, Long> {
}
