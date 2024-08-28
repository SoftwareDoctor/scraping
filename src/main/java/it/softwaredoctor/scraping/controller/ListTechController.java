package it.softwaredoctor.scraping.controller;

import it.softwaredoctor.scraping.model.ListaTech;
import it.softwaredoctor.scraping.service.ListTechService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tech")
public class ListTechController {

    private final ListTechService listTechService;

    @PostMapping("/new")
    public ResponseEntity<String> createTechList(@RequestBody List<ListaTech> listTech) {
        listTechService.createTechList(listTech);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tecnologie salvate e ordinate con successo");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListaTech>> getAllTechnologies() {
        List<ListaTech> technologies = listTechService.getAllTechnologies();
        return ResponseEntity.ok(technologies);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTechnology(@PathVariable Long id, @RequestParam String nameTecnology) {
        try {
            listTechService.updateTechnology(id, nameTecnology);
            return ResponseEntity.ok("Tecnologia aggiornata con successo");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTechnology(@PathVariable Long id) {
        listTechService.deleteTechnology(id);
        return ResponseEntity.ok("Tecnologia eliminata con successo");
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTechnology(@RequestBody ListaTech tecnologia) {
        listTechService.addTechnology(tecnologia);
        return ResponseEntity.status(HttpStatus.CREATED).body("Tecnologia aggiunta con successo");
    }

    @GetMapping("/reorder")
    public ResponseEntity<String> reorderTechnologiesBatch() {
        listTechService.reorderTechnologiesBatch();
        return ResponseEntity.ok("Batch di riordinamento eseguito con successo");
    }
}
