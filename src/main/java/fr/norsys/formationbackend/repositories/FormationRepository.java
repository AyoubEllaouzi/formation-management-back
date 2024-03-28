package fr.norsys.formationbackend.repositories;

import fr.norsys.formationbackend.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    List<Formation> findByTitreContaining(String titre);
}
