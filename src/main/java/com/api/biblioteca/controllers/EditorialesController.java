package com.api.biblioteca.controllers;

import com.api.biblioteca.models.Editorials;
import com.api.biblioteca.services.EditorialesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/editoriales")
public class EditorialesController {

    private final EditorialesService editorialesService;

    public EditorialesController(EditorialesService editorialesService) {
        this.editorialesService = editorialesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editorials> getEditorial(@PathVariable("id") int editorialId) {
        return new ResponseEntity<>(this.editorialesService.obtainEditorial(editorialId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Editorials>> getEditoriales() {
        return new ResponseEntity<>(this.editorialesService.listEditorials(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> insertarEditorial(@RequestBody Editorials editorials) {
        editorialesService.insertEditorial(editorials);
        return new ResponseEntity<>(Map.of("id", editorials.getEditorialId()), HttpStatus.CREATED);
    }
}
