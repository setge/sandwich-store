package com.setge.sandwichstore;

import com.setge.sandwichstore.data.IngredientRepository;
import com.setge.sandwichstore.domain.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.setge.sandwichstore.domain.Ingredient.Type;

@SpringBootApplication
public class SandwichStoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(SandwichStoreApplication.class, args);
    }

    @Bean // 빈 : 외부 라이브러리 클래스를 빈으로 등록할 때 사용
    public CommandLineRunner dataLoader(IngredientRepository repo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.save(new Ingredient("HOOA", "Honey Oat", Type.WRAP));
                repo.save(new Ingredient("FLAT", "Flat Bread", Type.WRAP));
                repo.save(new Ingredient("PORK", "Pork", Type.PROTEIN));
                repo.save(new Ingredient("CHME", "Chicken Meat", Type.PROTEIN));
                repo.save(new Ingredient("LETT", "Lettuce", Type.VEGGIES));
                repo.save(new Ingredient("TOMA", "Tomatoes", Type.VEGGIES));
                repo.save(new Ingredient("AMCH", "American Cheese", Type.CHEESE));
                repo.save(new Ingredient("MOCH", "Mozzarella Cheese", Type.CHEESE));
                repo.save(new Ingredient("RANC", "Ranch", Type.SAUCE));
                repo.save(new Ingredient("HOTC", "Hot Chilli", Type.SAUCE));
            }
        };
    }

}
