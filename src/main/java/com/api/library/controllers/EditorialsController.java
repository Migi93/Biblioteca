package com.api.library.controllers;

import com.api.library.models.Editorials;
import com.api.library.services.EditorialsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/editorials")
public class EditorialsController {

    private final EditorialsService editorialsService;

    public EditorialsController(EditorialsService editorialsService) {
        this.editorialsService = editorialsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorials> getEditorial(@PathVariable("id") int editorialId) {
        return new ResponseEntity<>(this.editorialsService.getEditorial(editorialId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Editorials>> getEditorials() {
        return new ResponseEntity<>(this.editorialsService.listEditorials(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Map<String, Integer>> insertEditorial(@RequestBody Editorials editorials) {
        editorialsService.insertEditorial(editorials);
        return new ResponseEntity<>(Map.of("id", editorials.getEditorialId()), HttpStatus.CREATED);
    }
}
