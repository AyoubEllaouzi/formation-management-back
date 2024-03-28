package fr.norsys.formationbackend.controllers;

import fr.norsys.formationbackend.entities.Formation;
import fr.norsys.formationbackend.services.FormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formations")
@CrossOrigin(origins = "http://localhost:4200")
public class FormationController {

    @Autowired
    private FormationService formationService;

    @PostMapping("")
    public ResponseEntity<Void> saveFormation(@RequestBody Formation formation) {
        formationService.addFormation(formation);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formation> updateFormation(@PathVariable Long id, @RequestBody Formation updatedFormation) {
        Formation formation = formationService.updateFormation(id, updatedFormation);
        if (formation != null) {
            return new ResponseEntity<>(formation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormation(@PathVariable Long id) {
        boolean deleted = formationService.deleteFormation(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formation> getFormation(@PathVariable Long id) {
        Formation formation = formationService.getFormation(id);
        if (formation != null) {
            return new ResponseEntity<>(formation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Formation>> getAllFormations() {
        List<Formation> formations = formationService.getAllFormations();
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @GetMapping("search/{search}")
    public ResponseEntity<List<Formation>> searchFormations(@PathVariable String search) {
        List<Formation> formations = formationService.searchFormation(search);
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }
}
