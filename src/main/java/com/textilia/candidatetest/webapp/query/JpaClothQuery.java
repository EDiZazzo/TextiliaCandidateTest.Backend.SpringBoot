package com.textilia.candidatetest.webapp.query;

import com.textilia.candidatetest.webapp.model.ClothItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaClothQuery extends JpaRepository<ClothItemEntity, Long> {

}
