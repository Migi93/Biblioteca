package com.api.biblioteca.services.impl;

import com.api.biblioteca.mappers.EditorialesMapper;
import com.api.biblioteca.models.Editoriales;
import com.api.biblioteca.services.EditorialesService;
import org.springframework.stereotype.Service;

@Service
public class EditorialesServiceImpl implements EditorialesService {

    EditorialesMapper editorialesMapper;

    public EditorialesServiceImpl(EditorialesMapper editorialesMapper) {
        this.editorialesMapper = editorialesMapper;
    }

    @Override
    public Editoriales obtenerEditorial(int editorialesId) {
        return editorialesMapper.getEditorial(editorialesId);
    }
}
