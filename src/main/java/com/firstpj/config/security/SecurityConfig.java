package com.firstpj.config.security;


import com.firstpj.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * packageName    : com.firstpj.config
 * fileName       : SecurityConfig
 * author         : hagjoon
 * date           : 2024-04-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-16        hagjoon       최초 생성
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    private static final String[] AUTH_WHITELIST = {
            "/api/**","/swagger-ui/**","/api-docs","/swagger-ui-custom.html",
            "/swagger-ui.html","/v2/api-docs","/swagger/","/swagger-resources/"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)-> csrf.disable());
        http.cors(Customizer.withDefaults());
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));
        http.formLogin((form)-> form.disable());
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling((exceptionHandling)-> exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomerAccessDeniedHandler()));

        http.authorizeHttpRequests(authorize ->authorize
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().permitAll());


        return http.build(); // HttpSecurity 설정을 기반으로 SecurityFilterChain 객체 생성 및 반환
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // CORS 설정을 위한 새로운 CorsConfiguration 객체 생성
        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        // "http://localhost:63342"을 요청을 허용할 출처(origin)로 설정
        configuration.setAllowCredentials(true);
        // 쿠키를 포함한 요청을 허용하도록 설정
        configuration.addAllowedHeader("*");
        // 모든 헤더를 요청 허용 헤더로 설정
        configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS"));
        // "GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS" 메소드를 요청 허용 메소드로 설정
        configuration.setMaxAge(3600L);
        // pre-flight 요청의 결과를 3600초(1시간) 동안 캐시할 수 있도록 설정

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // CORS 설정을 URL 기반으로 관리할 수 있는 UrlBasedCorsConfigurationSource 객체 생성
        source.registerCorsConfiguration("/**", configuration);
        // 모든 경로("/**")에 대해 위에서 정의한 CORS 설정(configuration)을 적용
        return source;
        // 설정이 완료된 CORS 구성 소스 객체를 반환
    }



    }
