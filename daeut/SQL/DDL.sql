CREATE TABLE board
(
  board_no       INT          NOT NULL AUTO_INCREMENT COMMENT '게시판 번호',
  board_title    VARCHAR(100) NOT NULL COMMENT '게시글 제목',
  board_content  TEXT         NULL     COMMENT '게시글 내용',
  board_reg_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '게시글 등록일자',
  board_upd_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '게시글 수정일자',
  board_views    INT          NOT NULL DEFAULT 0 COMMENT '게시글 조회수',
  board_like     INT          NULL     DEFAULT 0 COMMENT '게시글 좋아요',
  user_no        INT          NOT NULL COMMENT '사용자 번호',
  PRIMARY KEY (board_no)
) COMMENT '팁게시판';

CREATE TABLE chat
(
  chat_no       INT       NOT NULL AUTO_INCREMENT COMMENT '채팅 번호',
  chat_content  TEXT      NOT NULL COMMENT '채팅 내용',
  chat_reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '채팅 등록일자',
  user_no       INT       NOT NULL COMMENT '사용자 번호',
  PRIMARY KEY (chat_no)
) COMMENT '채팅';

CREATE TABLE files
(
  file_no          INT         NOT NULL AUTO_INCREMENT COMMENT '파일 번호',
  parent_table     VARCHAR(50) NOT NULL COMMENT '부모 테이블',
  parent_no        INT         NOT NULL COMMENT '부모 테이블 번호',
  file_name        TEXT        NOT NULL COMMENT '파일 이름',
  origin_file_name TEXT        NULL     COMMENT '파일 원본 이름',
  file_path        TEXT        NOT NULL COMMENT '파일 경로',
  file_size        INT         NOT NULL DEFAULT 0 COMMENT '파일 사이즈',
  file_reg_date    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '파일 등록일자',
  file_upd_date    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '파일 수정일자',
  file_code        INT         NOT NULL DEFAULT 0 COMMENT '파일 코드',
  PRIMARY KEY (file_no)
) COMMENT '파일';

CREATE TABLE partner
(
  partner_no      INT          NOT NULL AUTO_INCREMENT COMMENT '파트너 번호',
  partner_grade   INT          NULL     DEFAULT 0 COMMENT '파트너 별점',
  partner_reserve INT          NULL     DEFAULT 0 COMMENT '파트너 예약 횟수',
  partner_career  INT          NOT NULL COMMENT '파트너 경력',
  status          VARCHAR(100) NOT NULL COMMENT '파트너 승인 상태',
  introduce       TEXT         NULL     COMMENT '파트너 소개글',
  user_no         INT          NOT NULL COMMENT '사용자 번호',
  PRIMARY KEY (partner_no)
) COMMENT '파트너';

CREATE TABLE payment
(
  payment_no     INT          NOT NULL AUTO_INCREMENT COMMENT '결제 번호',
  payment_method VARCHAR(100) NOT NULL COMMENT '결제 방식',
  payment_price  INT          NOT NULL COMMENT '결제 총 가격',
  reservation_no INT          NOT NULL COMMENT '예약 번호',
  PRIMARY KEY (payment_no)
) COMMENT '결제';

CREATE TABLE persistent_logins
(
  series    VARCHAR(64) NOT NULL COMMENT '로그인 시리즈',
  username  VARCHAR(64) NOT NULL COMMENT '사용자 이름',
  token     VARCHAR(64) NOT NULL COMMENT '토큰',
  last_used TIMESTAMP   NOT NULL COMMENT '마지막 로그인',
  PRIMARY KEY (series)
) COMMENT '자동로그인';

ALTER TABLE persistent_logins
  ADD CONSTRAINT UQ_token UNIQUE (token);

CREATE TABLE reply
(
  reply_no       INT       NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
  parent_no      INT       NOT NULL COMMENT '부모 테이블 번호',
  reply_content  TEXT      NOT NULL COMMENT '댓글 내용',
  reply_reg_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 등록일자',
  reply_upd_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 수정일자',
  board_no       INT       NOT NULL COMMENT '게시판 번호',
  user_no        INT       NOT NULL COMMENT '사용자 번호',
  PRIMARY KEY (reply_no)
) COMMENT '댓글';

CREATE TABLE reservation
(
  reservation_no     INT         NOT NULL AUTO_INCREMENT COMMENT '예약 번호',
  reservation_status VARCHAR(50) NOT NULL COMMENT '예약 상태',
  reservation_time   TIMESTAMP   NOT NULL COMMENT '예약 시간',
  user_no            INT         NOT NULL COMMENT '사용자 번호',
  service_no         INT         NOT NULL COMMENT '서비스 번호',
  partner_no         INT         NOT NULL COMMENT '파트너 번호',
  PRIMARY KEY (reservation_no)
) COMMENT '예약';

CREATE TABLE review
(
  review_no       INT          NOT NULL AUTO_INCREMENT COMMENT '리뷰 번호',
  review_title    VARCHAR(100) NOT NULL COMMENT '리뷰 제목',
  review_content  TEXT         NOT NULL COMMENT '리뷰 내용',
  review_reg_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰 등록일자',
  review_rating   INT          NULL     COMMENT '별점',
  user_no         INT          NOT NULL COMMENT '사용자 번호',
  payment_no      INT          NOT NULL COMMENT '결제 번호',
  partner_no      INT          NOT NULL COMMENT '파트너 번호',
  PRIMARY KEY (review_no)
) COMMENT '후기';

CREATE TABLE service
(
  service_no       INT          NOT NULL AUTO_INCREMENT COMMENT '서비스 번호',
  service_category VARCHAR(100) NOT NULL COMMENT '서비스 분류',
  service_name     VARCHAR(100) NOT NULL COMMENT '서비스 이름',
  service_price    INT          NOT NULL COMMENT '서비스 가격',
  service_content  TEXT         NULL     COMMENT '서비스 내용',
  partner_no       INT          NOT NULL COMMENT '파트너 번호',
  PRIMARY KEY (service_no)
) COMMENT '서비스';

CREATE TABLE user_auth
(
   auth_no INT          NOT NULL AUTO_INCREMENT COMMENT '권한 번호',
  auth     VARCHAR(100) NOT NULL COMMENT '권한 분류',
  user_no  INT          NOT NULL COMMENT '사용자 번호',
  PRIMARY KEY ( auth_no)
) COMMENT '권한';

CREATE TABLE users
(
  user_no       INT          NOT NULL AUTO_INCREMENT COMMENT '사용자 번호',
  user_name     VARCHAR(100) NOT NULL COMMENT '사용자 이름',
  user_phone    VARCHAR(100) NOT NULL COMMENT '사용자 전화번호',
  user_birth    VARCHAR(100) NOT NULL COMMENT '사용자 생년월일',
  user_address  VARCHAR(300) NOT NULL COMMENT '사용자 주소',
  user_email    VARCHAR(100) NOT NULL COMMENT '사용자 이메일',
  user_gender   VARCHAR(50)  NULL     COMMENT '사용자 성별',
  user_id       VARCHAR(100) NOT NULL COMMENT '사용자 아이디',
  user_password VARCHAR(100) NOT NULL COMMENT '사용자 비밀번호',
  user_reg_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 등록일자',
  user_coupon   VARCHAR(200) NULL     COMMENT '사용자 쿠폰',
  user_upd_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 수정일자',
  PRIMARY KEY (user_no)
) COMMENT '사용자';

ALTER TABLE reply
  ADD CONSTRAINT FK_board_TO_reply
    FOREIGN KEY (board_no)
    REFERENCES board (board_no);

ALTER TABLE service
  ADD CONSTRAINT FK_partner_TO_service
    FOREIGN KEY (partner_no)
    REFERENCES partner (partner_no);

ALTER TABLE reservation
  ADD CONSTRAINT FK_users_TO_reservation
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE chat
  ADD CONSTRAINT FK_users_TO_chat
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE review
  ADD CONSTRAINT FK_users_TO_review
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE payment
  ADD CONSTRAINT FK_reservation_TO_payment
    FOREIGN KEY (reservation_no)
    REFERENCES reservation (reservation_no);

ALTER TABLE review
  ADD CONSTRAINT FK_payment_TO_review
    FOREIGN KEY (payment_no)
    REFERENCES payment (payment_no);

ALTER TABLE partner
  ADD CONSTRAINT FK_users_TO_partner
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE reservation
  ADD CONSTRAINT FK_service_TO_reservation
    FOREIGN KEY (service_no)
    REFERENCES service (service_no);

ALTER TABLE board
  ADD CONSTRAINT FK_users_TO_board
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE reply
  ADD CONSTRAINT FK_users_TO_reply
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE reservation
  ADD CONSTRAINT FK_partner_TO_reservation
    FOREIGN KEY (partner_no)
    REFERENCES partner (partner_no);

ALTER TABLE review
  ADD CONSTRAINT FK_partner_TO_review
    FOREIGN KEY (partner_no)
    REFERENCES partner (partner_no);

ALTER TABLE user_auth
  ADD CONSTRAINT FK_users_TO_user_auth
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);