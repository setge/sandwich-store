package com.setge.sandwichstore.sandwichs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 홈페이지_테스트() throws Exception {
        mockMvc.perform(get("/")) //
                .andExpect(status().isOk())  // 응답은 HTTP200(OK) 상태가 되어야 한다.
                .andExpect(view().name("home")) // 뷰의 이름은 home이다.
                .andExpect(content().string(containsString("Welcome to Sandwich Store"))); // 뷰에 Welcome이 포함되어야 한다.
    }

}
