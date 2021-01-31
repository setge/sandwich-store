package com.setge.sandwichstore.security;

import com.setge.sandwichstore.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;    // 아이디
    private String password;    // 비밀번호
    private String street;      // 주소
    private String zip;         // 우편번호
    private String fullname;    // 이름
    private String phone;       // 휴대폰

    public User toUser(PasswordEncoder passwordEncoder) { // 비밀번호릉 암호화해서 반환
        return new User(
                username, passwordEncoder.encode(password),
                street, zip, fullname, phone
        );
    }
}
