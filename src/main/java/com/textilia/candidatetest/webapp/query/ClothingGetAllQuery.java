package com.textilia.candidatetest.webapp.query;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ClothingGetAllQuery implements IClothingGetAllQuery{
private final JpaClothQuery query;

    public List<ClothItemResponseDTO> getAll() {
        List<ClothItemEntity> entities = query.findAll();

        return entities.stream()
                .map(ClothItemResponseDTO::new)
                .collect(Collectors.toList());
    }
}
