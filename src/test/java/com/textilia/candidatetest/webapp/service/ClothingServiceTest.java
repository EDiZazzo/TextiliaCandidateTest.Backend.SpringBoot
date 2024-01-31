package com.textilia.candidatetest.webapp.service;

import com.textilia.candidatetest.webapp.command.IClothingSaveCommand;
import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.query.IClothingGetAllQuery;
import com.textilia.candidatetest.webapp.validator.IClothItemRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClothingServiceTest {


    private IClothingSaveCommand clothingSaveCommand = Mockito.mock(IClothingSaveCommand.class);
    private IClothingGetAllQuery clothingGetAllQuery = Mockito.mock(IClothingGetAllQuery.class);

    private IClothItemRequestValidator clothItemRequestValidator = Mockito.mock(IClothItemRequestValidator.class);;

    private ClothingService clothingService = new ClothingService(clothingSaveCommand, clothingGetAllQuery, clothItemRequestValidator);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveClothingItem_ValidRequest() {
        // Mocking data
        ClothItemRequestDTO requestDTO = new ClothItemRequestDTO();
        requestDTO.setName("Shirt");
        requestDTO.setSize("M");
        requestDTO.setColor("Blue");

        Date date = new Date();
        ClothItemResponseDTO responseDTO = new ClothItemResponseDTO(new ClothItemEntity());

        // Mocking repository save method
        when(clothingSaveCommand.save(eq(requestDTO), any())).thenReturn(responseDTO);

        // Calling the service method
        ClothItemResponseDTO savedItem = clothingService.saveClothingItem(requestDTO);

        // Verifying that the repository save method was called with the correct parameters
        verify(clothingSaveCommand, times(1)).save(eq(requestDTO), any());

        // Verifying that the returned item is not null
        assertNotNull(savedItem);
    }

    @Test
    public void testSaveClothingItem_InvalidRequest() {
        // Mocking data
        ClothItemRequestDTO requestDTO = new ClothItemRequestDTO();

        // Mocking validation to return errors
        Errors errors = new BeanPropertyBindingResult(requestDTO, "clothItemRequestDTO");
        errors.rejectValue("name", "NotEmpty.clothItemRequestDTO.name", "Name cannot be empty");
        doThrow(new IllegalArgumentException("Invalid ClothItemRequestDTO")).when(clothItemRequestValidator).validate(eq(requestDTO), any(Errors.class));

        // Calling the service method and expecting an exception
        assertThrows(IllegalArgumentException.class, () -> clothingService.saveClothingItem(requestDTO));

        // Verifying that the repository save method was not called
        verify(clothingSaveCommand, never()).save(any(), any());
    }

    @Test
    public void testGetAllClothingItems() {
        // Mocking repository getAll method
        List<ClothItemResponseDTO> expectedItems = Arrays.asList(new ClothItemResponseDTO(new ClothItemEntity()), new ClothItemResponseDTO(new ClothItemEntity()));
        when(clothingGetAllQuery.getAll()).thenReturn(expectedItems);

        // Calling the service method
        List<ClothItemResponseDTO> actualItems = clothingService.getAllClothingItems();

        // Verifying that the returned list is not null and has the expected size
        assertNotNull(actualItems);
        assertEquals(expectedItems.size(), actualItems.size());

        // Verifying that the repository getAll method was called
        verify(clothingGetAllQuery, times(1)).getAll();
    }
}
