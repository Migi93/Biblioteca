package com.api.library.services.impl;

import com.api.library.exceptions.EditorialNotFoundexception;
import com.api.library.exceptions.ObjectFoundException;
import com.api.library.exceptions.RequiredMissingFieldException;
import com.api.library.exceptions.WorngLengthFieldException;
import com.api.library.models.Editorials;
import com.api.library.persistance.database.mappers.EditorialsMapper;
import com.api.library.services.EditorialsService;
import com.api.library.services.utils.ValidationsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialsServiceImpl implements EditorialsService {

    private final EditorialsMapper editorialsMapper;
    private final ValidationsUtils validationsUtils;
    private static final int AMOUNT_50 = 50;
    private static final int AMOUNT_100 = 100;

    public EditorialsServiceImpl(EditorialsMapper editorialsMapper) {
        validationsUtils = new ValidationsUtils();
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

    @Override
    public void insertEditorial(Editorials editorials) {
        existEditorial(editorials.getName());
        validationsUtils.validateLengthName(AMOUNT_50, editorials.getName().length(), "name");
        validationsUtils.validateIsEmpty(editorials.getName(), "name");
        validateAddress(editorials);
        validatePhone(editorials);
        validateEmail(editorials);
        this.editorialsMapper.insertEditorial(editorials);
    }

    @Override
    public void updateEditorial(Editorials editorials) {
        notExistEditorial(editorials.getEditorialId());
        validationsUtils.validateLengthName(AMOUNT_100, editorials.getName().length(), "name");
        validationsUtils.validateIsEmpty(editorials.getName(), "name");
        validateAddress(editorials);
        validatePhone(editorials);
        validateEmail(editorials);
        editorialsMapper.updateEditorial(editorials);
    }

    @Override
    public void deleteEditorial(int editorialId) {
        notExistEditorial(editorialId);
        editorialsMapper.deleteEditorial(editorialId);
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

    private void validateAddress(Editorials editorials) {
        if (editorials.getAddress().length() > 100) {
            throw new WorngLengthFieldException("address", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        if (editorials.getAddress().isEmpty()) {
            throw new RequiredMissingFieldException("address", HttpStatus.BAD_REQUEST);
        }
    }

    private void validatePhone(Editorials editorials) {
        if (editorials.getPhone().length() > 9) {
            throw new WorngLengthFieldException("phone", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        if (editorials.getPhone().isEmpty()) {
            throw new RequiredMissingFieldException("phone", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateEmail(Editorials editorials) {
        if (editorials.getEmail().length() > 50) {
            throw new WorngLengthFieldException("email", HttpStatus.PAYLOAD_TOO_LARGE);
        }
        if (editorials.getPhone().isEmpty()) {
            throw new RequiredMissingFieldException("email", HttpStatus.BAD_REQUEST);
        }
    }

}
