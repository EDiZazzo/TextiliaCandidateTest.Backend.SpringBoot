package com.textilia.candidatetest.webapp.query;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;


public interface IClothingGetAllQuery extends JpaRepository<ClothItemEntity, Long> {

    default List<ClothItemResponseDTO> getAll() {
        List<ClothItemEntity> entities = findAll();

        return entities.stream()
                .map(ClothItemResponseDTO::new)
                .collect(Collectors.toList());
    }
}
