package com.textilia.candidatetest.webapp.controller;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.service.IClothingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClothingControllerTest {

    @Mock
    private IClothingService clothingService;

    @InjectMocks
    private ClothingController clothingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddClothingItem() {
        // Mocking data
        ClothItemRequestDTO requestDTO = new ClothItemRequestDTO();
        requestDTO.setName("Shirt");
        requestDTO.setSize("M");
        requestDTO.setColor("Blue");

        ClothItemResponseDTO responseDTO = new ClothItemResponseDTO(new ClothItemEntity());
        responseDTO.setName("Shirt");
        responseDTO.setSize("M");
        responseDTO.setColor("Blue");

        // Mocking service method
        when(clothingService.saveClothingItem(eq(requestDTO))).thenReturn(responseDTO);

        // Calling the controller method
        ResponseEntity<ClothItemResponseDTO> responseEntity = clothingController.addClothingItem(requestDTO);

        // Verifying that the service method was called with the correct parameter
        verify(clothingService, times(1)).saveClothingItem(eq(requestDTO));

        // Verifying the response status and body
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());
    }

    @Test
    void testGetAllClothingItems() {
        // Mocking data
        List<ClothItemResponseDTO> expectedItems = Arrays.asList(
                new ClothItemResponseDTO(new ClothItemEntity()),
                new ClothItemResponseDTO(new ClothItemEntity())
        );

        // Mocking service method
        when(clothingService.getAllClothingItems()).thenReturn(expectedItems);

        // Calling the controller method
        ResponseEntity<List<ClothItemResponseDTO>> responseEntity = clothingController.getAllClothingItems();

        // Verifying that the service method was called
        verify(clothingService, times(1)).getAllClothingItems();

        // Verifying the response status and body
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedItems, responseEntity.getBody());
    }
}
