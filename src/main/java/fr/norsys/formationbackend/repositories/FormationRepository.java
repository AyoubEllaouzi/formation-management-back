package fr.norsys.formationbackend.repositories;

import fr.norsys.formationbackend.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepository extends JpaRepository<Formation,Long> {
}
