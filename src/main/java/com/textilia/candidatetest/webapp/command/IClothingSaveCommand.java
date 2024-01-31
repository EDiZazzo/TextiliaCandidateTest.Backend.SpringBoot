package com.textilia.candidatetest.webapp.command;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface IClothingSaveCommand extends JpaRepository<ClothItemEntity, Long> {

    default ClothItemResponseDTO save(ClothItemRequestDTO clothItemRequestDTO, Date date) {
        ClothItemEntity entity = new ClothItemEntity();
        entity.setName(clothItemRequestDTO.getName());
        entity.setSize(clothItemRequestDTO.getSize());
        entity.setColor(clothItemRequestDTO.getColor());
        entity.setCreatedTimestamp(date);
        entity.setUpdatedTimestamp(date);

        ClothItemEntity savedEntity = save(entity);

        return new ClothItemResponseDTO(savedEntity);
    }
}
