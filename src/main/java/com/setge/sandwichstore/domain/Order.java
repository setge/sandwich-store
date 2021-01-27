package com.setge.sandwichstore.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Order {

    private Long id; // 식별하기 위한 id
    private Date placedAt; // 주문일

    @NotBlank(message = "이름을 입력해주세요")
    private String deliveryName;

    @NotBlank(message = "주소를 입력해주세요")
    private String deliveryStreet;

    @NotBlank(message = "연락처를 입력해주세요")
    private String deliveryPhone;

//    @NotBlank(message = "")
//    private String deliveryCity;
//    private String deliveryState;
//    private String deliveryZip;

    @CreditCardNumber(message = "카드번호가 유효하지 않습니다.")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "MM/YY 형식을 지정해야합니다.")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "잘못된 CVV입니다.")
    private String ccCVV;

}
