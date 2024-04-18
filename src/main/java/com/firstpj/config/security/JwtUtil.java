package com.firstpj.config.security;

import com.firstpj.member.model.CustomUserInfoModel;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

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
            log.error("만료된 토큰",e);
            return false;
        }catch (Exception e){
            log.error("알수 없는 예외 발생.",e);
            return false;
        }
    }

  public Authentication getAuthentication(String jwtToken){
        String memberEmail = getUserEmail(jwtToken);
      UserDetails userDetails = userDetailsService.loadUserByUsername(memberEmail);
      return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
  }


    public String getUserEmail(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();

    }

    public Claims getUserInfoFromToken(String jwtToken){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwtToken).getBody();
    }

}
