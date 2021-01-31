package com.setge.sandwichstore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 웹 보안 활성화 / WebSecurityConfigurerAdapter를 확장한 빈으로 생성하자.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // HTTP보안을 구성하는 메서
        http.authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')") // 샌드위치 주문은 회원만..
                .antMatchers("/", "/**")
                .access("permitAll()") // 그 외 인증되지 않은 사용자도 접근허용
                .and()
                .formLogin() // 로그인 페이지를 구성하기 위해 호출
                .loginPage("/login") // 로그인 페이지로 이동
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{ // 사용자의 인증정보를 구성하는 메서드
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }
}

