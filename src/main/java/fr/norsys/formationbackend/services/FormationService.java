package fr.norsys.formationbackend.services;

import fr.norsys.formationbackend.entities.Formation;
import fr.norsys.formationbackend.repositories.FormationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FormationService {
    private FormationRepository formationRepository;

    public void addFormation(Formation formation){
        formationRepository.save(formation);
    }

    public Formation updateFormation(Long id, Formation newFormation){
        Formation formation = formationRepository.findById(id).orElse(null);
        if (formation != null){
            formation.setTitre(newFormation.getTitre());
            formation.setDescription(newFormation.getDescription());
            formation.setDateDebut(newFormation.getDateDebut());
            formation.setDateFin(newFormation.getDateFin()); // Fix here: set dateFin with newFormation's dateFin
            formation = formationRepository.save(formation); // Save the updated formation
        }
        return formation;
    }

    public boolean deleteFormation(Long id){
        Formation formation = formationRepository.findById(id).orElse(null);
        if (formation!=null){
            formationRepository.delete(formation);
            return true;
        }
         return false;
    }

    public Formation getFormation(Long id){
        return formationRepository.findById(id).orElse(null);
    }

    public List<Formation> getAllFormations(){
        return formationRepository.findAll();
    }
}
