package com.textilia.candidatetest.webapp.command;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


public interface IClothingSaveCommand {

    ClothItemResponseDTO save(ClothItemRequestDTO clothItemRequestDTO, Date date);

}
