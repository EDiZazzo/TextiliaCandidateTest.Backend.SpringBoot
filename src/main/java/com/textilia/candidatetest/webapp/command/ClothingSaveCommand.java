package com.textilia.candidatetest.webapp.command;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ClothingSaveCommand implements IClothingSaveCommand{
    private final JpaClothCommand command;

    public ClothItemResponseDTO save(ClothItemRequestDTO clothItemRequestDTO, Date date) {
        ClothItemEntity entity = new ClothItemEntity();
        entity.setName(clothItemRequestDTO.getName());
        entity.setSize(clothItemRequestDTO.getSize());
        entity.setColor(clothItemRequestDTO.getColor());
        entity.setCreatedTimestamp(date);
        entity.setUpdatedTimestamp(date);

        ClothItemEntity savedEntity = command.save(entity);

        return new ClothItemResponseDTO(savedEntity);
    }
}
