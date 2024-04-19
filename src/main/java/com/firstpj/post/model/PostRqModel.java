package com.firstpj.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

/**
 * packageName    : com.firstpj.member.model
 * fileName       : PostRqModel
 * author         : hagjoon
 * date           : 2024-04-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-19        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRqModel {
    private Integer postId;
    private Integer memberId;
    private String title;
    private String content;
    private String author;
    private String createAt;

    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
