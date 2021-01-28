package com.setge.sandwichstore.data;


import com.setge.sandwichstore.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    /**
     * CrudRepository : 기본적인 CRUD메서드를 가지고 있다.
     * <저장되는 개체 타입, ID 속성 타입>
     */

}
