package com.textilia.candidatetest.webapp.command;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClothCommand extends JpaRepository<ClothItemEntity, Long> {
}
