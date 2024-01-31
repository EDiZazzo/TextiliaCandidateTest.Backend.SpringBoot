package com.textilia.candidatetest.webapp.service;

import com.textilia.candidatetest.webapp.command.IClothingSaveCommand;
import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.query.IClothingGetAllQuery;
import com.textilia.candidatetest.webapp.validator.IClothItemRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClothingServiceTest {

    @Mock
    private IClothingSaveCommand clothingCommandRepository;

    @Mock
    private IClothingGetAllQuery clothingQueryRepository;

    @Mock
    private IClothItemRequestValidator clothItemRequestValidator;

    @InjectMocks
    private ClothingService clothingService;

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

        // Mocking validation to return no errors
        Errors errors = new BeanPropertyBindingResult(requestDTO, "clothItemRequestDTO");
        doNothing().when(clothItemRequestValidator).validate(eq(requestDTO), any(Errors.class));

        // Mocking repository save method
        when(clothingCommandRepository.save(eq(requestDTO), eq(date))).thenReturn(responseDTO);

        // Calling the service method
        ClothItemResponseDTO savedItem = clothingService.saveClothingItem(requestDTO);

        // Verifying that the repository save method was called with the correct parameters
        verify(clothingCommandRepository, times(1)).save(eq(requestDTO), eq(date));

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
        verify(clothingCommandRepository, never()).save(any(), any());
    }

    @Test
    public void testGetAllClothingItems() {
        // Mocking repository getAll method
        List<ClothItemResponseDTO> expectedItems = Arrays.asList(new ClothItemResponseDTO(new ClothItemEntity()), new ClothItemResponseDTO(new ClothItemEntity()));
        when(clothingQueryRepository.getAll()).thenReturn(expectedItems);

        // Calling the service method
        List<ClothItemResponseDTO> actualItems = clothingService.getAllClothingItems();

        // Verifying that the returned list is not null and has the expected size
        assertNotNull(actualItems);
        assertEquals(expectedItems.size(), actualItems.size());

        // Verifying that the repository getAll method was called
        verify(clothingQueryRepository, times(1)).getAll();
    }
}
