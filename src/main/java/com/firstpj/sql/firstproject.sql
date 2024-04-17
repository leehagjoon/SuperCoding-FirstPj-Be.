CREATE TABLE comments (
                          comments_id int NOT NULL,
                          post_id int NOT NULL,
                          member_id int NOT NULL,
                          content varchar(255) NOT NULL,
                          created_at datetime(6) NOT NULL,
                          PRIMARY KEY (comments_id),
                          KEY cm_post_id_idx (post_id),
                          KEY cm_member_id_idx (member_id),
                          CONSTRAINT cm_member_id FOREIGN KEY (member_id) REFERENCES member (member_id),
                          CONSTRAINT cm_post_id FOREIGN KEY (post_id) REFERENCES post (post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='댓글'
CREATE TABLE member (
                        member_id int NOT NULL COMMENT '회원 ID',
                        email varchar(25) NOT NULL COMMENT '이메일',
                        password varchar(50) NOT NULL COMMENT '비밀번호',
                        author varchar(10) NOT NULL COMMENT '이름',
                        role varchar(30) NOT NULL COMMENT '권한',
                        PRIMARY KEY (member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원'
CREATE TABLE post (
                      post_id int NOT NULL COMMENT '게시글 ID',
                      member_id int NOT NULL COMMENT '회원ID',
                      title varchar(50) NOT NULL COMMENT '제목',
                      content varchar(500) NOT NULL COMMENT '본문',
                      author varchar(10) NOT NULL COMMENT '이름',
                      create_at datetime(6) NOT NULL COMMENT '생성시간',
                      PRIMARY KEY (post_id),
                      KEY post_member_id_idx (member_id),
                      CONSTRAINT post_member_id FOREIGN KEY (member_id) REFERENCES member (member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글'