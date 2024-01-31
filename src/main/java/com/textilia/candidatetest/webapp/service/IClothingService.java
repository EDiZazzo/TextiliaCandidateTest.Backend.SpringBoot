package com.textilia.candidatetest.webapp.service;

import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;

import java.util.List;

public interface IClothingService {

    ClothItemResponseDTO saveClothingItem(ClothItemRequestDTO clothItem);

    List<ClothItemResponseDTO> getAllClothingItems();
}