package fr.norsys.formationbackend.repositories;

import fr.norsys.formationbackend.entities.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdherentRepository extends JpaRepository<Adherent, Long> {
    List<Adherent> findByLastNameContaining(String keyword);

    List<Adherent> findByFormationId(Long formationId);
}
