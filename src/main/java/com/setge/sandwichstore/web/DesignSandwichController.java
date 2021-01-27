package com.setge.sandwichstore.web;

import com.setge.sandwichstore.data.IngredientRepository;
import com.setge.sandwichstore.domain.Ingredient;
import com.setge.sandwichstore.domain.Ingredient.Type;
import com.setge.sandwichstore.domain.Sandwich;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignSandwichController {

    private final IngredientRepository ingredientsRepository;

    public DesignSandwichController(IngredientRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("HOOA", "Honey Oat", Type.WRAP),
//                new Ingredient("FLAT", "Flat Bread", Type.WRAP),
//                new Ingredient("PORK", "Pork", Type.PROTEIN),
//                new Ingredient("CHME", "Chicken Meat", Type.PROTEIN),
//                new Ingredient("LETT", "Lettuce", Type.VEGGIES),
//                new Ingredient("TOMA", "Tomatoes", Type.VEGGIES),
//                new Ingredient("AMCH", "American Cheese", Type.CHEESE),
//                new Ingredient("MOCH", "Mozzarella Cheese", Type.CHEESE),
//                new Ingredient("RANC", "Ranch", Type.SAUCE),
//                new Ingredient("HOTC", "Hot Chilli", Type.SAUCE)
//        );

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientsRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("sandwich", new Sandwich());

        return "design";
    }

    // 식자재의 유형을 필터링 한다.
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Sandwich design, Errors errors) {
        if (errors.hasErrors()) {
            return "design"; // 값이 유효하지 않으면 다시 입력
        }

        // 이 지점에서 샌드위치 디자인(선택된 식자재 내역)을 저장한다.
        // 추후에 추가
        log.info("Processing design : " + design);

        return "redirect:/orders/current";
    }

}
