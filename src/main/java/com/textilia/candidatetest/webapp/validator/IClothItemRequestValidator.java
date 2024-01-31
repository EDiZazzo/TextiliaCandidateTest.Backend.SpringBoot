package com.textilia.candidatetest.webapp.validator;

import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface IClothItemRequestValidator extends Validator {
    void validate(ClothItemRequestDTO requestDTO, Errors errors);
    void validateNotBlank(String field, String value, Errors errors);

    void validateSize(String field, String value, int maxSize, Errors errors);

    String capitalize(String str);
}

