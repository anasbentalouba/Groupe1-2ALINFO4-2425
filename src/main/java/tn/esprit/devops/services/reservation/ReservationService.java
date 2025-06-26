package tn.esprit.devops.services.reservation;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.devops.dao.entities.Chambre;
import tn.esprit.devops.dao.entities.Etudiant;
import tn.esprit.devops.dao.entities.Reservation;
import tn.esprit.devops.dao.repositories.ChambreRepository;
import tn.esprit.devops.dao.repositories.EtudiantRepository;
import tn.esprit.devops.dao.repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService implements IReservationService {
    ReservationRepository repo;
    ChambreRepository chambreRepository;
    EtudiantRepository etudiantRepository;

    @Override
    public Reservation addOrUpdate(Reservation r) {
        return repo.save(r);
    }

    @Override
    public List<Reservation> findAll() {
        return repo.findAll();
    }

    @Override
    public Reservation findById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        repo.deleteById(id);
    }

    @Override
    public void delete(Reservation r) {
        repo.delete(r);
    }

    public LocalDate getDateDebutAU() {
        LocalDate dateDebutAU;
        int year = LocalDate.now().getYear() % 100;
        if (LocalDate.now().getMonthValue() <= 7) {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + (year - 1)), 9, 15);
        } else {
            dateDebutAU = LocalDate.of(Integer.parseInt("20" + year), 9, 15);
        }
        return dateDebutAU;
    }

    public LocalDate getDateFinAU() {
        LocalDate dateFinAU;
        int year = LocalDate.now().getYear() % 100;
        if (LocalDate.now().getMonthValue() <= 7) {
            dateFinAU = LocalDate.of(Integer.parseInt("20" + year), 6, 30);
        } else {
            dateFinAU = LocalDate.of(Integer.parseInt("20" + (year + 1)), 6, 30);
        }
        return dateFinAU;
    }

  @Override
public Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(Long numChambre, long cin) {
    Chambre chambre = chambreRepository.findByNumeroChambre(numChambre);
    Etudiant etudiant = etudiantRepository.findByCin(cin);
 
    int nombreReservations = chambreRepository
            .countReservationsByIdChambreAndReservationsAnneeUniversitaireBetween(
                    chambre.getIdChambre(), getDateDebutAU(), getDateFinAU());
 
    int capacityMaximale = switch (chambre.getTypeC()) {
        case SIMPLE -> 1;
        case DOUBLE -> 2;
        case TRIPLE -> 3;
    };
 
    if (nombreReservations >= capacityMaximale) {
        log.info("Chambre " + chambre.getTypeC() + " remplie !");
        return null; 
    }
 
    String idReservation = getDateDebutAU().getYear() + "/" + getDateFinAU().getYear() + "-" +
            chambre.getBloc().getNomBloc() + "-" + chambre.getNumeroChambre() + "-" + etudiant.getCin();
 
    Reservation reservation = Reservation.builder()
            .estValide(true)
            .anneeUniversitaire(LocalDate.now())
            .idReservation(idReservation)
            .build();
 
    if (reservation.getEtudiants() == null) {
        reservation.setEtudiants(new java.util.ArrayList<>());
    }
 
    reservation.getEtudiants().add(etudiant);
 
    reservation = repo.save(reservation);
 
    if (chambre.getReservations() == null) {
        chambre.setReservations(new java.util.ArrayList<>());
    }
    chambre.getReservations().add(reservation);
    chambreRepository.save(chambre);
 
    return reservation;
}


    @Override
    public long getReservationParAnneeUniversitaire(LocalDate debutAnne, LocalDate finAnne) {
        return repo.countByAnneeUniversitaireBetween(debutAnne, finAnne);
    }

    @Override
    public String annulerReservation(long cinEtudiant) {
        Reservation r = repo.findByEtudiantsCinAndEstValide(cinEtudiant,
                true);
        Chambre c = chambreRepository.findByReservationsIdReservation
                (r.getIdReservation());
        c.getReservations().remove(r);
        chambreRepository.save(c);
        repo.delete(r);
        return "La réservation " + r.getIdReservation()
                + " est annulée avec succés";
    }


}
