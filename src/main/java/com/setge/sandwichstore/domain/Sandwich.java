package com.setge.sandwichstore.domain;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Sandwich {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 생성
    private Long id;

    private Date createAt; // 생성일

    @NotNull
    @Size(min=2, message = "샌드위치 이름은 2글자 이상 입력해주세요.")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1, message = "1개 이상 선택해주세요")
    private List<Ingredient> ingredients;

    @PrePersist
    void createAt() {
        this.createAt = new Date();
    }


}
