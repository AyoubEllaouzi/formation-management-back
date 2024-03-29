package fr.norsys.formationbackend.services;

import fr.norsys.formationbackend.entities.Adherent;
import fr.norsys.formationbackend.entities.Formation;
import fr.norsys.formationbackend.repositories.AdherentRepository;
import fr.norsys.formationbackend.repositories.FormationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class AdherentService {
    private AdherentRepository adherentRepository;
    private FormationRepository formationRepository;
    public void addAdherent(Adherent adherent){
        adherentRepository.save(adherent);
    }

    public Adherent updateAdherent(Long id, Adherent newAdherent){
        Adherent adherent = adherentRepository.findById(id).orElse(null);
        if (adherent != null){
            adherent.setFirstName(newAdherent.getFirstName());
            adherent.setLastName(newAdherent.getLastName());
            adherent.setAddress(newAdherent.getAddress());
            adherent.setPhone(newAdherent.getPhone());
            adherent.setEmail(newAdherent.getEmail());
            adherent = adherentRepository.save(adherent);
        }
        return adherent;
    }


    public boolean deleteAdherent(Long id){
        Adherent adherent = adherentRepository.findById(id).orElse(null);
        if (adherent!=null){
            adherentRepository.delete(adherent);
            return true;
        }
        return false;
    }

    public Adherent getAdherent(Long id){
        return adherentRepository.findById(id).orElse(null);
    }
    public List<Adherent> getAllAdherents(){
        return adherentRepository.findAll();
    }

    public List<Adherent> searchAdherent(String keyword){
        return adherentRepository.findByLastNameContaining(keyword);
    }

    public void affectFormation(Long idAdherent, Long idFormation) {
        Adherent adherent = adherentRepository.findById(idAdherent).orElse(null);
        Formation formation = formationRepository.findById(idFormation).orElse(null);
        assert adherent != null;
        adherent.setFormation(formation);
        adherentRepository.save(adherent);
    }


    public List<Adherent> getAdherentsInFormation(Long formationId) {
        return adherentRepository.findByFormationId(formationId);
    }
}
