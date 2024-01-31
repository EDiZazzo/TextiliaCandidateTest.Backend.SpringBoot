package com.textilia.candidatetest.webapp.query;

import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;

import java.util.List;

public interface IClothingGetAllQuery {

    List<ClothItemResponseDTO> getAll();
}
