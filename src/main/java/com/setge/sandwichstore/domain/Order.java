package com.setge.sandwichstore.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Sandwich_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @NotBlank(message = "우쳔번호를 입력해주세요.")
    private String deliveryZip;

    @CreditCardNumber(message = "카드번호가 유효하지 않습니다.")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "MM/YY 형식을 지정해야합니다.")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "잘못된 CVV입니다.")
    private String ccCVV;

    @ManyToMany(targetEntity = Sandwich.class)
    private List<Sandwich> sandwichs = new ArrayList<>(); // 사용자가 선택한 토핑된 샌드위치를 리스트에 저장(여러개 일 수 있으므로)

    public void addDesign(Sandwich design) {
        this.sandwichs.add(design);
    }

    @PrePersist
    void placeAt() {
        this.placedAt = new Date();

    }

}
