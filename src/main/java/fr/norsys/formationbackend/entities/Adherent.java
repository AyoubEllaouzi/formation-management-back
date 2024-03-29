package fr.norsys.formationbackend.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Adherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    @JsonIgnoreProperties("adherents")
    private Formation formation;
}
