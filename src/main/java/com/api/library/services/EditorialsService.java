package com.api.library.services;

import com.api.library.models.Editorials;

import java.util.List;

public interface EditorialsService {

    Editorials getEditorial(int editorialesId);

    List<Editorials> listEditorials();

    void insertEditorial(Editorials editorials);
}
