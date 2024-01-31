package com.textilia.candidatetest.webapp.service;

import com.textilia.candidatetest.webapp.command.IClothingSaveCommand;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.query.IClothingGetAllQuery;
import com.textilia.candidatetest.webapp.query.JpaClothQuery;
import com.textilia.candidatetest.webapp.validator.IClothItemRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.List;

@Service
public class ClothingService implements IClothingService {

    private final IClothingSaveCommand clothingSaveCommand;
    private final IClothingGetAllQuery clothingGetAllQuery;
    private final IClothItemRequestValidator clothItemRequestValidator;

    @Autowired
    public ClothingService(
            IClothingSaveCommand clothingSaveCommand,
            IClothingGetAllQuery clothingGetAllQuery,
            IClothItemRequestValidator clothItemRequestValidator) {
        this.clothingSaveCommand = clothingSaveCommand;
        this.clothingGetAllQuery = clothingGetAllQuery;
        this.clothItemRequestValidator = clothItemRequestValidator;
    }

    @Override
    public ClothItemResponseDTO saveClothingItem(ClothItemRequestDTO clothItem) {
        validateClothItemRequest(clothItem);

        Date date = new Date();
        return clothingSaveCommand.save(clothItem, date);
    }

    @Override
    public List<ClothItemResponseDTO> getAllClothingItems() {
        return clothingGetAllQuery.getAll();
    }

    private void validateClothItemRequest(ClothItemRequestDTO clothItemRequestDTO) {
        Errors errors = new BeanPropertyBindingResult(clothItemRequestDTO, "clothItemRequestDTO");
        clothItemRequestValidator.validate(clothItemRequestDTO, errors);

        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid ClothItemRequestDTO");
        }
    }
}
