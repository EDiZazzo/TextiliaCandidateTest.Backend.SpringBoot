package com.textilia.candidatetest.webapp.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;

import com.textilia.candidatetest.webapp.command.IClothingSaveCommand;
import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemRequestDTO;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public class IClothingSaveCommandTest {

    @Mock
    private JpaRepository<ClothItemEntity, Long> repositoryMock;

    @InjectMocks
    private IClothingSaveCommand clothingSaveCommand;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Arrange
        ClothItemRequestDTO requestDTO = new ClothItemRequestDTO();
        requestDTO.setName("TestCloth");
        requestDTO.setSize("M");
        requestDTO.setColor("Blue");

        Date currentDate = new Date();

        ClothItemEntity entityToSave = new ClothItemEntity();
        entityToSave.setName(requestDTO.getName());
        entityToSave.setSize(requestDTO.getSize());
        entityToSave.setColor(requestDTO.getColor());
        entityToSave.setCreatedTimestamp(currentDate);
        entityToSave.setUpdatedTimestamp(currentDate);

        ClothItemEntity savedEntity = new ClothItemEntity();
        savedEntity.setId(1L);  // Assuming an ID is generated during save
        savedEntity.setName(requestDTO.getName());
        savedEntity.setSize(requestDTO.getSize());
        savedEntity.setColor(requestDTO.getColor());
        savedEntity.setCreatedTimestamp(currentDate);
        savedEntity.setUpdatedTimestamp(currentDate);

        when(repositoryMock.save(any(ClothItemEntity.class))).thenReturn(savedEntity);

        // Act
        ClothItemResponseDTO responseDTO = clothingSaveCommand.save(requestDTO, currentDate);

        // Assert
        assertEquals(savedEntity.getName(), responseDTO.getName());
        assertEquals(savedEntity.getSize(), responseDTO.getSize());
        assertEquals(savedEntity.getColor(), responseDTO.getColor());
    }
}
