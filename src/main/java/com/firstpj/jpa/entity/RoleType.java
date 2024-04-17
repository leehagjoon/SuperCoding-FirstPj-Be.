package com.firstpj.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.firstpj.jpa.entity
 * fileName       : RoleType
 * author         : hagjoon
 * date           : 2024-04-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-17        hagjoon       최초 생성
 */
public enum RoleType {
    USER(Authority.USER);
    private final String authority;
    RoleType(String authority) {
        this.authority = authority;
    }
    public String getAuthority(){
        return this.authority;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
    }

    public static RoleType fromString(String role){
        switch (role){
            case "ROLE_USER":
                return USER;
        }
        return null;
    }
}
