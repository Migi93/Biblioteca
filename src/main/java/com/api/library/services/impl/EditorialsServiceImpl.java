package com.api.library.services.impl;

import com.api.library.exceptions.EditorialNotFoundexception;
import com.api.library.exceptions.ObjectFoundException;
import com.api.library.exceptions.RequiredMissingFieldException;
import com.api.library.exceptions.WorngLengthFieldException;
import com.api.library.models.Editorials;
import com.api.library.persistance.database.mappers.EditorialsMapper;
import com.api.library.services.EditorialsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialsServiceImpl implements EditorialsService {

    EditorialsMapper editorialsMapper;

    public EditorialsServiceImpl(EditorialsMapper editorialsMapper) {
        this.editorialsMapper = editorialsMapper;
    }

    @Override
    public Editorials getEditorial(int editorialId) {
        notExistEditorial(editorialId);
        return editorialsMapper.getEditorial(editorialId);
    }

    public List<Editorials> listEditorials() {
        return editorialsMapper.getEditorials();
    }

    public void insertEditorial(Editorials editorials) {
        existEditorial(editorials.getName());
        validateNameEditorial(editorials);
        this.editorialsMapper.insertEditorial(editorials);
    }

    //VALIDACIONES
    private void existEditorial(String nameEditorial) {
        if (editorialsMapper.existEditorial(nameEditorial) > 0) {
            throw new ObjectFoundException("Editorial", HttpStatus.CONFLICT);
        }
    }

    private void notExistEditorial(int editorialId) {
        if (editorialsMapper.notExistEditorial(editorialId) < 1) {
            throw new EditorialNotFoundexception("Editorial", HttpStatus.NOT_FOUND);
        }
    }

    private void validateNameEditorial(Editorials editorials) {
        if (editorials.getName().length() > 50) {
            throw new WorngLengthFieldException("nombre", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        if (editorials.getName().isEmpty()) {
            throw new RequiredMissingFieldException("nombre", HttpStatus.BAD_REQUEST);
        }
    }


}
