package com.textilia.candidatetest.webapp.database;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import com.textilia.candidatetest.webapp.model.ClothItemResponseDTO;
import com.textilia.candidatetest.webapp.query.ClothingGetAllQuery;
import com.textilia.candidatetest.webapp.query.JpaClothQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

public class IClothingGetAllQueryTest {

    private JpaClothQuery clothingGetAllQuery = Mockito.mock(JpaClothQuery.class);

    private ClothingGetAllQuery query = new ClothingGetAllQuery(clothingGetAllQuery);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<ClothItemEntity> mockEntities = new ArrayList<>();
        mockEntities.add(new ClothItemEntity(1L, "Shirt", "M", "Blue", new Date(), new Date()));
        mockEntities.add(new ClothItemEntity(2L, "Jeans", "S", "Black", new Date(), new Date()));

        when(clothingGetAllQuery.findAll()).thenReturn(mockEntities);

        // Act
        List<ClothItemResponseDTO> responseDTOs = query.getAll();

        // Assert
        assertEquals(mockEntities.size(), responseDTOs.size());

        for (int i = 0; i < mockEntities.size(); i++) {
            ClothItemEntity mockEntity = mockEntities.get(i);
            ClothItemResponseDTO responseDTO = responseDTOs.get(i);

            assertAll("Cloth Item",
                    () -> assertEquals(mockEntity.getName(), responseDTO.getName()),
                    () -> assertEquals(mockEntity.getSize(), responseDTO.getSize()),
                    () -> assertEquals(mockEntity.getColor(), responseDTO.getColor()),
                    () -> assertEquals(mockEntity.getCreatedTimestamp(), responseDTO.getCreatedTimestamp()),
                    () -> assertEquals(mockEntity.getUpdatedTimestamp(), responseDTO.getUpdatedTimestamp())
            );
        }

        // Verify interaction with the repository
        verify(clothingGetAllQuery, times(1)).findAll();
    }
}
