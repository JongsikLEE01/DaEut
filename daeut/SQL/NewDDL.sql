-- Active: 1715242304860@@127.0.0.1@3306@joeun

-- 결제 로직


DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS cancel;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS chat_rooms;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;

DROP TABLE IF EXISTS partner;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS user_auth;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_social;

        

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

CREATE TABLE cancel
(
  cancel_no      int          NOT NULL AUTO_INCREMENT COMMENT '취소 번호',
  reason         TEXT         NULL     COMMENT '취소 사유',
  cancel_amount  int          NULL     DEFAULT 0 COMMENT '환불 금액',
  confirmed      TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '취소 승인 여부',
  refund         TINYINT(1)   NOT NULL DEFAULT 0 COMMENT '환불 처리 여부',
  cancel_account VARCHAR(100) NULL     COMMENT '환불 계좌번호',
  cancel_number  VARCHAR(100) NULL     COMMENT '환불 계좌은행',
  cancel_name    VARCHAR(100) NULL     COMMENT '예금주',
  cancel_date    TIMESTAMP    NULL     COMMENT '취소일자',
  completed_date TIMESTAMP    NULL     COMMENT '처리일자',
  reg_date       TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '등록일자',
  upd_date       TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '수정일자',
  orders_no      VARCHAR(50)  NOT NULL COMMENT '예약 번호',
  PRIMARY KEY (cancel_no)
) COMMENT '취소';

CREATE TABLE cart
(
  cart_no       int         NOT NULL AUTO_INCREMENT COMMENT '장바구니번호',
  cart_amount   INT         NOT NULL DEFAULT 1 COMMENT '수량',
  cart_reg_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
  cart_upd_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
  service_no    INT         NOT NULL COMMENT '서비스 번호',
  user_no       INT         NOT NULL COMMENT '사용자 번호',
  partner_name  VARCHAR(50) NOT NULL COMMENT '파트너 이름',
  PRIMARY KEY (cart_no)
) COMMENT '장바구니';

CREATE TABLE chat
(
  chat_no       INT          NOT NULL AUTO_INCREMENT COMMENT '채팅 번호',
  chat_content  TEXT         NOT NULL COMMENT '채팅 내용',
  chat_reg_date TIMESTAMP    NOT NULL COMMENT '채팅 등록일자',
  user_no       INT          NOT NULL COMMENT '사용자 번호',
  room_no       VARCHAR(100) NOT NULL COMMENT '방 번호',
  PRIMARY KEY (chat_no)
) COMMENT '채팅';

CREATE TABLE chat_rooms
(
  room_no    VARCHAR(100) NOT NULL COMMENT '방 번호',
  reg_date   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '방 등록일자',
  room_out   BOOLEAN      NOT NULL DEFAULT 0 COMMENT '나가기 여부, 0=안나감 1=나감',
  user_no    INT          NOT NULL COMMENT '사용자 번호',
  partner_no INT          NOT NULL COMMENT '파트너 번호',
  title      VARCHAR(50)  NOT NULL COMMENT '채팅방 이름',
  PRIMARY KEY (room_no)
) COMMENT '채팅방';

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

CREATE TABLE order_item
(
  item_no    VARCHAR(50) NOT NULL COMMENT '예약 항목 번호',
  quantity   INT         NOT NULL DEFAULT 1 COMMENT '수량',
  price      INT         NOT NULL DEFAULT 0 COMMENT '단가',
  amount     INT         NULL     COMMENT '총계',
  upd_date   TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT '항목 수정일자',
  reg_date   TIMESTAMP   NOT NULL DEFAULT current_timestamp COMMENT '항목 등록일자',
  orders_no  VARCHAR(50) NOT NULL COMMENT '예약 번호',
  service_no INT         NOT NULL COMMENT '서비스 번호',
  PRIMARY KEY (item_no)
) COMMENT '예약항목';


CREATE TABLE orders
(
  orders_no      VARCHAR(50)  NOT NULL COMMENT '예약 번호',
  user_no        INT          NOT NULL COMMENT '사용자 번호',
  order_status   VARCHAR(50)  NULL DEFAULT 'PENDING' COMMENT '예약 상태',
  total_quantity INT          NOT NULL COMMENT '총 수량',
  total_price    INT          NOT NULL COMMENT '총 가격',
  upd_date       TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '예약 수정일자',
  reg_date       TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '예약 등록일자',
  total_count    INT          NOT NULL COMMENT '총 항목수',
  title          VARCHAR(100) NULL     COMMENT '예약 제목',
  PRIMARY KEY (orders_no)
) COMMENT '예약';


CREATE TABLE partner
(
  partner_no      INT          NOT NULL AUTO_INCREMENT COMMENT '파트너 번호',
  partner_grade   INT          NULL     DEFAULT 0 COMMENT '파트너 별점',
  partner_reserve INT          NULL     DEFAULT 0 COMMENT '파트너 예약 횟수',
  partner_career  VARCHAR(100) NOT NULL COMMENT '파트너 경력',
  introduce       TEXT         NULL     COMMENT '파트너 소개글',
  user_no         INT          NOT NULL COMMENT '사용자 번호',
  PRIMARY KEY (partner_no)
) COMMENT '파트너';

CREATE TABLE payment
(
  payment_no      INT          NOT NULL AUTO_INCREMENT COMMENT '결제 번호',
  payment_method  VARCHAR(100) NOT NULL COMMENT '결제 방식',
  status          VARCHAR(100) NULL     COMMENT '결제 상태',
  pay_date        DATETIME     NULL     DEFAULT current_timestamp COMMENT '결제일',
  reg_date        DATETIME     NULL     DEFAULT current_timestamp COMMENT '결제 등록일',
  upd_date        DATETIME     NULL     DEFAULT current_timestamp COMMENT '결제 수정일',
  orders_no       VARCHAR(50)  NOT NULL COMMENT '예약 번호',
  service_date    DATETIME     NULL     COMMENT '서비스 신청일',
  service_address VARCHAR(100) NULL     COMMENT '서비스 주소',
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
  reply_no       INT          NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
  parent_no      INT          NOT NULL COMMENT '부모 테이블 번호',
  reply_content  TEXT         NOT NULL COMMENT '댓글 내용',
  reply_reg_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 등록일자',
  reply_upd_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글 수정일자',
  board_no       INT          NOT NULL COMMENT '게시판 번호',
  user_no        INT          NOT NULL COMMENT '사용자 번호',
  user_id        VARCHAR(100) NOT NULL COMMENT '사용자 아이디',
  PRIMARY KEY (reply_no)
) COMMENT '댓글';
-- ALTER TABLE reply ADD COLUMN user_id VARCHAR(255);

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
  service_no      INT          NOT NULL COMMENT '서비스 번호',
  PRIMARY KEY (review_no)
) COMMENT '후기';

CREATE TABLE service
(
  service_no       INT          NOT NULL AUTO_INCREMENT COMMENT '서비스 번호',
  service_category VARCHAR(100) NOT NULL COMMENT '서비스 분류',
  service_name     VARCHAR(100) NOT NULL COMMENT '서비스 이름',
  service_price    INT          NOT NULL COMMENT '서비스 가격',
  service_content  TEXT         NULL     COMMENT '서비스 내용',
  upd_date         TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '서비스 수정일자',
  reg_date         TIMESTAMP    NOT NULL DEFAULT current_timestamp COMMENT '서비스 등록일자',
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
  user_birth    TIMESTAMP    NOT NULL COMMENT '사용자 생년월일',
  user_address  VARCHAR(300) NOT NULL COMMENT '사용자 주소',
  user_email    VARCHAR(100) NOT NULL COMMENT '사용자 이메일',
  user_gender   VARCHAR(50)  NULL     COMMENT '사용자 성별',
  user_id       VARCHAR(100) NOT NULL COMMENT '사용자 아이디',
  user_password VARCHAR(100) NOT NULL COMMENT '사용자 비밀번호',
  user_reg_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 등록일자',
  user_coupon   VARCHAR(200) NULL     COMMENT '사용자 쿠폰',
  user_upd_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 수정일자',
  enabled       INT          NULL     DEFAULT 1 COMMENT '계정 활성화',
  status        INT          NOT NULL DEFAULT 0 COMMENT '상태',
  PRIMARY KEY (user_no)
) COMMENT '사용자';

CREATE TABLE user_social (
    `ID` CHAR(36) PRIMARY KEY,
    `USERNAME` VARCHAR(100) NOT NULL,
    `PROVIDER` VARCHAR(50) NOT NULL,
    `SOCIAL_ID` VARCHAR(255) NOT NULL,
    `NAME` VARCHAR(100) NOT NULL,
    `EMAIL` VARCHAR(200) DEFAULT NULL,
    `PICTURE` TEXT DEFAULT NULL, 
    `CREATED_AT` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_AT` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT '소셜로그인';

-- *********************** 제약 조건 ***********************


-- board
ALTER TABLE board
  ADD CONSTRAINT FK_users_TO_board
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

-- cancel
ALTER TABLE cancel
  ADD CONSTRAINT FK_orders_TO_cancel
    FOREIGN KEY (orders_no)
    REFERENCES orders (orders_no);

-- cart
ALTER TABLE cart
  ADD CONSTRAINT FK_service_TO_cart
    FOREIGN KEY (service_no)
    REFERENCES service (service_no);

ALTER TABLE cart
  ADD CONSTRAINT FK_users_TO_cart
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

-- chat
ALTER TABLE chat
  ADD CONSTRAINT FK_users_TO_chat
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

ALTER TABLE chat
  ADD CONSTRAINT FK_chat_rooms_TO_chat
    FOREIGN KEY (room_no)
    REFERENCES chat_rooms (room_no);

-- chat_rooms
ALTER TABLE chat_rooms
  ADD CONSTRAINT FK_users_TO_chat_rooms
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

-- order_item
ALTER TABLE order_item
  ADD CONSTRAINT FK_orders_TO_order_item
    FOREIGN KEY (orders_no)
    REFERENCES orders (orders_no);

ALTER TABLE order_item
  ADD CONSTRAINT FK_service_TO_order_item
    FOREIGN KEY (service_no)
    REFERENCES service (service_no);

-- orders
ALTER TABLE orders
  ADD CONSTRAINT FK_users_TO_orders
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

-- payment
ALTER TABLE payment
  ADD CONSTRAINT FK_orders_TO_payment
    FOREIGN KEY (orders_no)
    REFERENCES orders (orders_no);

-- reply
ALTER TABLE reply
  ADD CONSTRAINT FK_board_TO_reply
    FOREIGN KEY (board_no)
    REFERENCES board (board_no);

ALTER TABLE reply
  ADD CONSTRAINT FK_users_TO_reply
    FOREIGN KEY (user_no)
    REFERENCES users (user_no);

-- review
ALTER TABLE review
  ADD CONSTRAINT FK_users_TO_review
    FOREIGN KEY (user_no)
    REFERENCES users (user_no)
    ON DELETE CASCADE;

ALTER TABLE review
  ADD CONSTRAINT FK_payment_TO_review
    FOREIGN KEY (payment_no)
    REFERENCES payment (payment_no)
    ON DELETE CASCADE;

ALTER TABLE review
  ADD CONSTRAINT FK_partner_TO_review
    FOREIGN KEY (partner_no)
    REFERENCES partner (partner_no)
    ON DELETE CASCADE;

ALTER TABLE review
  ADD CONSTRAINT FK_service_TO_review
    FOREIGN KEY (service_no)
    REFERENCES service (service_no)
    ON DELETE CASCADE;

-- service
ALTER TABLE service
  ADD CONSTRAINT FK_partner_TO_service
    FOREIGN KEY (partner_no)
    REFERENCES partner (partner_no);

-- user_auth
ALTER TABLE user_auth
  ADD CONSTRAINT FK_users_TO_user_auth
    FOREIGN KEY (user_no)
    REFERENCES users (user_no) ON DELETE CASCADE;

-- partner
ALTER TABLE partner
  ADD CONSTRAINT FK_users_TO_partner
    FOREIGN KEY (user_no)
    REFERENCES users (user_no) ON DELETE CASCADE;