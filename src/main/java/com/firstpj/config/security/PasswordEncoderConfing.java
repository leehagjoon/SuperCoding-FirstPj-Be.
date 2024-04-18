package com.firstpj.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * packageName    : com.firstpj.config
 * fileName       : PasswordEncoderConfing
 * author         : hagjoon
 * date           : 2024-04-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18        hagjoon       최초 생성
 */
@Configuration
public class PasswordEncoderConfing {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
