package com.textilia.candidatetest.webapp.service;

import com.textilia.candidatetest.webapp.command.IClothingSaveCommand;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.query.IClothingGetAllQuery;
import com.textilia.candidatetest.webapp.validator.ClothItemRequestValidator;
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

    private final IClothingSaveCommand clothingCommandRepository;
    private final IClothingGetAllQuery clothingQueryRepository;
    private final Validator clothItemRequestValidator;

    @Autowired
    public ClothingService(
            IClothingSaveCommand clothingCommandRepository,
            IClothingGetAllQuery clothingQueryRepository,
            IClothItemRequestValidator clothItemRequestValidator) {
        this.clothingCommandRepository = clothingCommandRepository;
        this.clothingQueryRepository = clothingQueryRepository;
        this.clothItemRequestValidator = clothItemRequestValidator;
    }

    @Override
    public ClothItemResponseDTO saveClothingItem(ClothItemRequestDTO clothItem) {
        validateClothItemRequest(clothItem);

        Date date = new Date();
        return clothingCommandRepository.save(clothItem, date);
    }

    @Override
    public List<ClothItemResponseDTO> getAllClothingItems() {
        return clothingQueryRepository.getAll();
    }

    private void validateClothItemRequest(ClothItemRequestDTO clothItemRequestDTO) {
        Errors errors = new BeanPropertyBindingResult(clothItemRequestDTO, "clothItemRequestDTO");
        clothItemRequestValidator.validate(clothItemRequestDTO, errors);

        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid ClothItemRequestDTO");
        }
    }
}
