package com.textilia.candidatetest.webapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "cloth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Size cannot be empty")
    @Size(max = 10, message = "Size cannot exceed 10 characters")
    @Column(name = "size", nullable = false, length = 10)
    private String size;

    @NotBlank(message = "Color cannot be empty")
    @Size(max = 10, message = "Color cannot exceed 10 characters")
    @Column(name = "color", nullable = false, length = 10)
    private String color;

    @NotNull(message = "Creation timestamp cannot be null")
    @Column(name = "created_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimestamp;

    @NotNull(message = "Update timestamp cannot be null")
    @Column(name = "updated_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTimestamp;
}
