package com.textilia.candidatetest.webapp.validator;

import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;

@Component
public class ClothItemRequestValidator implements IClothItemRequestValidator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ClothItemRequestDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClothItemRequestDTO clothItemRequestDTO = (ClothItemRequestDTO) target;

        validate(clothItemRequestDTO, errors);
    }

    @Override
    public void validate(ClothItemRequestDTO clothItemRequestDTO, Errors errors) {

        validateNotBlank("name", clothItemRequestDTO.getName(), errors);
        validateNotBlank("size", clothItemRequestDTO.getSize(), errors);
        validateNotBlank("color", clothItemRequestDTO.getColor(), errors);

        validateSize("name", clothItemRequestDTO.getName(), 50, errors);
        validateSize("size", clothItemRequestDTO.getSize(), 10, errors);
        validateSize("color", clothItemRequestDTO.getColor(), 10, errors);
    }

    public void validateNotBlank(String field, String value, Errors errors) {
        if (value == null || value.isEmpty()) {
            errors.rejectValue(field, "NotEmpty.clothItemRequestDTO." + field, capitalize(field) + " cannot be empty");
        }
    }

    public void validateSize(String field, String value, int maxSize, Errors errors) {
        if (value != null && value.length() > maxSize) {
            errors.rejectValue(field, "Size.clothItemRequestDTO." + field, capitalize(field) + " cannot exceed " + maxSize + " characters");
        }
    }

    public String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
