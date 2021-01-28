package com.setge.sandwichstore.web;

import com.setge.sandwichstore.data.IngredientRepository;
import com.setge.sandwichstore.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> { // String -> Ingredient

    private IngredientRepository ingredientRepo;


    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String  id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        // 식자재를 찾지 못하면 null을 optional에 감싸서 반환
        return optionalIngredient.isPresent() ? optionalIngredient.get() : null;
    }

}
