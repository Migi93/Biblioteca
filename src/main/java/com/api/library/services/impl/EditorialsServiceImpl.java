package com.api.library.services.impl;

import com.api.library.exceptions.EditorialNotFoundexception;
import com.api.library.exceptions.ObjectFoundException;
import com.api.library.models.Editorials;
import com.api.library.persistance.database.mappers.EditorialsMapper;
import com.api.library.services.EditorialsService;
import com.api.library.services.Enum.Amount;
import com.api.library.services.utils.ValidationsUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialsServiceImpl implements EditorialsService {

    private final EditorialsMapper editorialsMapper;
    private final ValidationsUtils validationsUtils;

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
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_50.getValue(), editorials.getName().length(), "name");
        validationsUtils.validateNotIsEmpty(editorials.getName(), "name");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_100.getValue(), editorials.getAddress().length(), "address");
        validationsUtils.validateNotIsEmpty(editorials.getAddress(), "address");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_9.getValue(), editorials.getPhone().length(), "phone");
        validationsUtils.validateNotIsEmpty(editorials.getPhone(), "phone");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_50.getValue(), editorials.getPhone().length(), "phone");
        editorialsMapper.updateEditorial(editorials);
        this.editorialsMapper.insertEditorial(editorials);
    }

    @Override
    public void updateEditorial(Editorials editorials) {
        notExistEditorial(editorials.getEditorialId());
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_50.getValue(), editorials.getName().length(), "name");
        validationsUtils.validateNotIsEmpty(editorials.getName(), "name");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_100.getValue(), editorials.getAddress().length(), "address");
        validationsUtils.validateNotIsEmpty(editorials.getAddress(), "address");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_9.getValue(), editorials.getPhone().length(), "phone");
        validationsUtils.validateNotIsEmpty(editorials.getPhone(), "phone");
        validationsUtils.validateLengthAttribute(Amount.AMOUNT_50.getValue(), editorials.getPhone().length(), "phone");
        editorialsMapper.updateEditorial(editorials);
    }

    @Override
    public void deleteEditorial(int editorialId) {
        notExistEditorial(editorialId);
        editorialsMapper.deleteEditorial(editorialId);
    }

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

}
