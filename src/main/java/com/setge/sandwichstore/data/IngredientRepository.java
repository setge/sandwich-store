package com.setge.sandwichstore.data;


import com.setge.sandwichstore.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
