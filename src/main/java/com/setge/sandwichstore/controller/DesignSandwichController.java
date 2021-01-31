package com.setge.sandwichstore.controller;

import com.setge.sandwichstore.data.IngredientRepository;
import com.setge.sandwichstore.data.SandwichRepository;
import com.setge.sandwichstore.data.UserRepository;
import com.setge.sandwichstore.domain.Ingredient;
import com.setge.sandwichstore.domain.Ingredient.Type;
import com.setge.sandwichstore.domain.Order;
import com.setge.sandwichstore.domain.Sandwich;
import com.setge.sandwichstore.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignSandwichController {

    private final IngredientRepository ingredientsRepository;
    private final SandwichRepository sandwichRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String showDesignForm(Model model, Principal principal) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientsRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        return "design";
    }

    // 식자재의 유형을 필터링 한다.
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "sandwich")
    public Sandwich sandwich() {
        return new Sandwich();
    }

    @PostMapping
    public String processDesign(@Valid Sandwich design, Errors errors, @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design"; // 값이 유효하지 않으면 다시 입력
        }

        Sandwich saved = sandwichRepository.save(design); // 사용자가 요청한 토핑을 저장한다.
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

}
