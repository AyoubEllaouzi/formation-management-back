package fr.norsys.formationbackend.controllers;


import fr.norsys.formationbackend.entities.Adherent;
import fr.norsys.formationbackend.services.AdherentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adherents")
@CrossOrigin(origins = "http://localhost:4200")
public class AdherentController {

    @Autowired
    private AdherentService adherentService;

    @PostMapping("")
    public ResponseEntity<Void> saveAdherent(@RequestBody Adherent adherent) {
        adherentService.addAdherent(adherent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable Long id, @RequestBody Adherent updatedAdherent) {
        Adherent adherent = adherentService.updateAdherent(id, updatedAdherent);
        if (adherent != null) {
            return new ResponseEntity<>(adherent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdherent(@PathVariable Long id) {
        boolean deleted = adherentService.deleteAdherent(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherent(@PathVariable Long id) {
        Adherent adherent = adherentService.getAdherent(id);
        if (adherent != null) {
            return new ResponseEntity<>(adherent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Adherent>> getAllAdherents() {
        List<Adherent> adherents = adherentService.getAllAdherents();
        return new ResponseEntity<>(adherents, HttpStatus.OK);
    }

    @GetMapping("search/{search}")
    public ResponseEntity<List<Adherent>> searchAdherents(@PathVariable String search) {
        List<Adherent> adherents = adherentService.searchAdherent(search);
        return new ResponseEntity<>(adherents, HttpStatus.OK);
    }

    @PutMapping("/affect/{idAdherent}/{idFormation}")
    public ResponseEntity<Void> affectFormation(@PathVariable Long idAdherent, @PathVariable Long idFormation) {
        adherentService.affectFormation(idAdherent, idFormation);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/formation/{id}")
    public ResponseEntity<List<Adherent>> getAdherentsInFormation(@PathVariable Long id) {
        List<Adherent> adherents = adherentService.getAdherentsInFormation(id);
        return ResponseEntity.ok(adherents);
    }
}
