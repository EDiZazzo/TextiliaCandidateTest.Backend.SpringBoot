package com.textilia.candidatetest.webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClothItemResponseDTO {

    private String name;
    private String size;
    private String color;
    private Date createdTimestamp;
    private Date updatedTimestamp;

    public ClothItemResponseDTO(ClothItemEntity entity) {
        this.setName(entity.getName());
        this.setSize(entity.getSize());
        this.setColor(entity.getColor());
        this.setCreatedTimestamp(entity.getCreatedTimestamp());
        this.setUpdatedTimestamp(entity.getUpdatedTimestamp());
    }
}