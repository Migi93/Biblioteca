package com.api.biblioteca.services;

import com.api.biblioteca.models.Editorials;

import java.util.List;

public interface EditorialesService {

    Editorials obtainEditorial(int editorialesId);

    List<Editorials> listEditorials();

    void insertEditorial(Editorials editorials);
}
