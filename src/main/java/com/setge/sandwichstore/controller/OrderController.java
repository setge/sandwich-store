package com.setge.sandwichstore.controller;

import com.setge.sandwichstore.data.OrderRepository;
import com.setge.sandwichstore.domain.Order;
import com.setge.sandwichstore.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getUsername());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryFullname() == null) {
            order.setDeliveryFullname(user.getFullname());
        }
        if (order.getDeliveryPhone() == null) {
            order.setDeliveryPhone(user.getPhoneNumber());
        }
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm"; // 입력이 유효하지 않으면 다시 입력
        }

        order.setUser(user); // 로그인 되어 있는 사용자의 정보를 order객체에 저장한다.

        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
