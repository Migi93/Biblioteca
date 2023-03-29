package com.api.biblioteca.controllers;

import com.api.biblioteca.models.Editoriales;
import com.api.biblioteca.services.EditorialesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editoriales")
public class EditorialesController {

    private final EditorialesService editorialesService;

    public EditorialesController(EditorialesService editorialesService) {
        this.editorialesService = editorialesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editoriales> getEditorial(@PathVariable("id") int editorialId) {
        return new ResponseEntity<>(editorialesService.obtenerEditorial(editorialId), HttpStatus.OK);
    }
}
