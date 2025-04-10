package tn.esprit.DevOps.DAO.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name = "T_UNIVERSITE")
@Getter
@Setter
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUniversite;
    @OneToOne(cascade = CascadeType.ALL) //ajout, Modif et suppression
    Foyer foyer;

}