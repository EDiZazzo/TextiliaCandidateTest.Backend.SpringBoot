package com.textilia.candidatetest.webapp.controller;


import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.service.IClothingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothing")
public class ClothingController implements IClothingController {

    private final IClothingService clothingService;

    @Autowired
    public ClothingController(IClothingService clothingService) {
        this.clothingService = clothingService;
    }

    @PostMapping("/add")
    public ResponseEntity<ClothItemResponseDTO> addClothingItem(@RequestBody ClothItemRequestDTO clothItem) {

        ClothItemResponseDTO savedItem = clothingService.saveClothingItem(clothItem);

        return new ResponseEntity<>(savedItem, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ClothItemResponseDTO>> getAllClothingItems() {

        List<ClothItemResponseDTO> allItems = clothingService.getAllClothingItems();

        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }
}