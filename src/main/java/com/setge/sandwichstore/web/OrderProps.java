package com.setge.sandwichstore.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@ConfigurationProperties(prefix = "sandwich.orders")
@Data
@Validated
public class OrderProps {
    /**
     * OrderProps : 주문과 관련된 구성 속성을 모아 놓았다.
     */

    @Min(value = 5, message = "5와 25사이여야 합니다.")
    @Max(value = 25, message = "5와 25사이여야 합니다.")
    private int pageSize = 20;


}
