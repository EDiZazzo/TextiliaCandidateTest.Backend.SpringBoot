package com.textilia.candidatetest.webapp;

import com.textilia.candidatetest.webapp.command.ClothingSaveCommand;
import com.textilia.candidatetest.webapp.command.IClothingSaveCommand;
import com.textilia.candidatetest.webapp.command.JpaClothCommand;
import com.textilia.candidatetest.webapp.query.ClothingGetAllQuery;
import com.textilia.candidatetest.webapp.query.IClothingGetAllQuery;
import com.textilia.candidatetest.webapp.query.JpaClothQuery;
import com.textilia.candidatetest.webapp.service.ClothingService;
import com.textilia.candidatetest.webapp.service.IClothingService;
import com.textilia.candidatetest.webapp.validator.IClothItemRequestValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public IClothingService clothingService(IClothingSaveCommand clothingSaveCommand,
                                            IClothingGetAllQuery clothingGetAllQuery,
                                            IClothItemRequestValidator clothItemRequestValidator) {
        return new ClothingService(clothingSaveCommand,
                clothingGetAllQuery,
                clothItemRequestValidator);
    }

    @Bean
    public IClothingGetAllQuery clothingGetAllQuery(JpaClothQuery query){
        return new ClothingGetAllQuery(query);
    }

    @Bean
    public IClothingSaveCommand clothingSaveCommand(JpaClothCommand command){
        return new ClothingSaveCommand(command);
    }
}
