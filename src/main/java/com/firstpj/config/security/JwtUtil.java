package com.firstpj.config.security;

import com.fasterxml.jackson.core.JsonParser;
import com.firstpj.member.model.CustomUserInfoModel;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKey;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * packageName    : com.firstpj.jwt
 * fileName       : JwtUtil
 * author         : hagjoon
 * date           : 2024-04-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-18        hagjoon       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private String secretKey;

    private long tokenValidMillisecond = 1000L * 60 * 60;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    public void setUp(){
        secretKey = Base64.getEncoder()
                .encodeToString("super-coding-FirstProject-Back-End".getBytes());
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("X-AUTH-TOKEN");
    }
    public String createToken(String email){
       Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public boolean validateToken(String jwtToken){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            Date experationDate = claims.getExpiration();
            Date now = new Date();
            return experationDate != null && experationDate.after(now);
        }catch (ExpiredJwtException e){
            log.info("만료된 토큰");
            return false;
        }catch (Exception e){
            log.info("토큰이 없습니다.");
            return false;
        }
    }

  public Authentication getAuthentication(String jwtToken){
        String memberEmail = getUserEmail(jwtToken);
      UserDetails userDetails = userDetailsService.loadUserByUsername(memberEmail);
      return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
  }


    public String getUserEmail(String jwtToken) {

        return  Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();

    }



}
