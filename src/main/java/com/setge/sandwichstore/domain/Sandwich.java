package com.setge.sandwichstore.domain;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Sandwich {

    private Long id; // 식별하기 위한 id
    private Date createAt; // 생성일

    @NotNull
    @Size(min=2, message = "샌드위치 이름은 2글자 이상 입력해주세요.")
    private String name;

    @Size(min=1, message = "1개 이상 선택해주세요")
    private List<String> ingredients;

}
