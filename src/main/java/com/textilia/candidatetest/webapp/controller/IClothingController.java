package com.textilia.candidatetest.webapp.controller;

import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Cloth", description = "Textilia Cloth Api")
@Validated
public interface IClothingController {

    @Operation(
            summary = "Insert new clothing item",
            operationId = "insertNewCloth",
            description = "Inserts a new clothing item and returns the updated list of all clothing items.",
            tags = {"Cloth"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping("/add")
    @CrossOrigin
    ResponseEntity<ClothItemResponseDTO> addClothingItem(
            @Parameter(name = "clothingItemDTO", description = "The clothing item to be added.", required = true) @RequestBody ClothItemRequestDTO clothItem);

    @Operation(
            summary = "Get all clothing items",
            operationId = "getAllClothItems",
            description = "Retrieves the entire list of clothing items.",
            tags = {"Cloth"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")})
    @GetMapping("/getAll")
    @CrossOrigin
    ResponseEntity<List<ClothItemResponseDTO>> getAllClothingItems();
}