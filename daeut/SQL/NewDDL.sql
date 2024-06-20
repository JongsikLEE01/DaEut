-- Active: 1717658424847@@127.0.0.1@3306@daeut


DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS cancel;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS chat_rooms;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;

DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS partner;
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
  PRIMARY KEY (reply_no)
) COMMENT '댓글';

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
    REFERENCES users (user_no) ON DELETE CASCADE;

-- -- cancel
-- ALTER TABLE cancel
--   ADD CONSTRAINT FK_orders_TO_cancel
--     FOREIGN KEY (orders_no)
--     REFERENCES orders (orders_no);

-- -- cart
-- ALTER TABLE cart
--   ADD CONSTRAINT FK_service_TO_cart
--     FOREIGN KEY (service_no)
--     REFERENCES service (service_no);

-- ALTER TABLE cart
--   ADD CONSTRAINT FK_users_TO_cart
--     FOREIGN KEY (user_no)
--     REFERENCES users (user_no);

-- -- chat
-- ALTER TABLE chat
--   ADD CONSTRAINT FK_users_TO_chat
--     FOREIGN KEY (user_no)
--     REFERENCES users (user_no);

-- ALTER TABLE chat
--   ADD CONSTRAINT FK_chat_rooms_TO_chat
--     FOREIGN KEY (room_no)
--     REFERENCES chat_rooms (room_no);

-- -- chat_rooms
-- ALTER TABLE chat_rooms
--   ADD CONSTRAINT FK_users_TO_chat_rooms
--     FOREIGN KEY (user_no)
--     REFERENCES users (user_no);

-- -- order_item
-- ALTER TABLE order_item
--   ADD CONSTRAINT FK_orders_TO_order_item
--     FOREIGN KEY (orders_no)
--     REFERENCES orders (orders_no);

-- ALTER TABLE order_item
--   ADD CONSTRAINT FK_service_TO_order_item
--     FOREIGN KEY (service_no)
--     REFERENCES service (service_no);

-- -- orders
-- ALTER TABLE orders
--   ADD CONSTRAINT FK_users_TO_orders
--     FOREIGN KEY (user_no)
--     REFERENCES users (user_no);

-- -- payment
-- ALTER TABLE payment
--   ADD CONSTRAINT FK_orders_TO_payment
--     FOREIGN KEY (orders_no)
--     REFERENCES orders (orders_no);

-- -- reply
-- ALTER TABLE reply
--   ADD CONSTRAINT FK_board_TO_reply
--     FOREIGN KEY (board_no)
--     REFERENCES board (board_no) ON DELETE CASCADE;

-- ALTER TABLE reply
--   ADD CONSTRAINT FK_users_TO_reply
--     FOREIGN KEY (user_no)
--     REFERENCES users (user_no);

-- -- review
-- ALTER TABLE review
--   ADD CONSTRAINT FK_users_TO_review
--     FOREIGN KEY (user_no)
--     REFERENCES users (user_no)
--     ON DELETE CASCADE;

-- ALTER TABLE review
--   ADD CONSTRAINT FK_payment_TO_review
--     FOREIGN KEY (payment_no)
--     REFERENCES payment (payment_no)
--     ON DELETE CASCADE;

-- ALTER TABLE review
  -- ADD CONSTRAINT FK_partner_TO_review
  --   FOREIGN KEY (partner_no)
  --   REFERENCES partner (partner_no)
  --   ON DELETE CASCADE;

ALTER TABLE review
  ADD CONSTRAINT FK_service_TO_review
    FOREIGN KEY (service_no)
    REFERENCES service (service_no) ON DELETE CASCADE;

-- service
ALTER TABLE service
  ADD CONSTRAINT FK_partner_TO_service
    FOREIGN KEY (partner_no)
    REFERENCES partner (partner_no) ON DELETE CASCADE;

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

-- service 제약 조건 삭제
-- ALTER TABLE service
-- DROP CONSTRAINT FK_partner_TO_service;

-- ⭐ 샘플 데이터
-- ------------------------------------------- 회원 샘플 ---------------------------------------------------------------
-- 사용자로 시연할 샘플
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password)
VALUES ("정다운", "01076672354", "2000-01-01", "인천광역시 남동구", "tkwk36@naver.com", "female", "tkwk36", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92");
INSERT INTO user_auth ( user_no,  auth )
VALUES ( 1, 'ROLE_USER' );
-- 파트너로 만들 회원
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password)
VALUES ("김조은", "01012345678", "2000-01-01", "인천광역시 부평구", "joeun@naver.com", "male", "joeun", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92");
INSERT INTO user_auth ( user_no,  auth )
VALUES ( 2, 'ROLE_USER' );
-- 관리자
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password)
VALUES ("관리자", "01012345678", "2000-01-01", "인천광역시 부평구", "admin@naver.com", "male", "admin", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92");
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_PARTNER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_ADMIN' );


INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password)
VALUES 
    ("사용자1", "01012345678", "2000-01-01", "인천광역시 부평구", "user1@naver.com", "male", "user1", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자2", "01023456789", "1999-02-02", "서울특별시 강남구", "user2@gmail.com", "female", "user2", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자3", "01034567890", "1998-03-03", "부산광역시 해운대구", "user3@daum.net", "male", "user3", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자4", "01045678901", "1997-04-04", "대구광역시 중구", "user4@naver.com", "female", "user4", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자5", "01056789012", "1996-05-05", "광주광역시 서구", "user5@gmail.com", "male", "user5", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자6", "01067890123", "1995-06-06", "대전광역시 유성구", "user6@daum.net", "female", "user6", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자7", "01078901234", "1994-07-07", "울산광역시 남구", "user7@naver.com", "male", "user7", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자8", "01089012345", "1993-08-08", "세종특별자치시", "user8@gmail.com", "female", "user8", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자9", "01090123456", "1992-09-09", "경기도 성남시", "user9@daum.net", "male", "user9", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자10", "01001234567", "1991-10-10", "전라북도 전주시", "user10@naver.com", "female", "user10", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("사용자11", "01012345679", "1990-11-11", "경상남도 창원시", "user11@gmail.com", "male", "user11", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92");
INSERT INTO user_auth ( user_no,  auth )
VALUES 
	( 4, 'ROLE_USER' ),
	( 5, 'ROLE_USER' ),
	( 6, 'ROLE_USER' ),
	( 7, 'ROLE_USER' ),
	( 8, 'ROLE_USER' ),
	( 9, 'ROLE_USER' ),
	( 10, 'ROLE_USER' ),
	( 11, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth )
VALUES 
( 12, 'ROLE_USER' ),
( 13, 'ROLE_USER' ),
( 14, 'ROLE_USER' );
    
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password)
VALUES 
    ("파트너1", "01012345678", "2000-01-01", "인천광역시 부평구", "partner1@naver.com", "male", "partner1", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92"),
    ("파트너2", "01023456789", "1999-02-02", "서울특별시 강남구", "partner2@gmail.com", "female", "partner2", "$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92");
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 15, 'ROLE_USER' ); 
UPDATE users SET status = 1 WHERE user_no = 15;
UPDATE users SET status = 2 WHERE user_no = 15;
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 15, 'ROLE_PARTNER' );
INSERT INTO partner (partner_career, introduce, user_no) VALUES ("일반 청소 2년" , "안녕하세요" , 15);  -- partner_no = 1

INSERT INTO user_auth ( user_no,  auth ) VALUES ( 16, 'ROLE_USER' );
UPDATE users SET status = 1 WHERE user_no = 16;
UPDATE users SET status = 2 WHERE user_no = 16;
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 16, 'ROLE_PARTNER' );
INSERT INTO partner (partner_career, introduce, user_no) VALUES ("특수 방역 5년" , "안녕하세요" , 16);  -- partner_no = 2
-- ----------------------------------------------------------------------------------------------------------------

-- ------------------------------------------- 예약 게시글 샘플 ---------------------------------------------------------------
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[청소] 화장실 청소', 300, '테스트데이터, 청소 서비스 입니다.', 1);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('빨래','[빨래] 빨래 전문', 300, '테스트데이터, 청소 서비스 입니다.', 1);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('방역','[방역] 집 청소', 300, '테스트데이터, 청소 서비스 입니다.', 1);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('보안','[보안] CCTV', 300, '테스트데이터, 청소 서비스 입니다.', 1);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('기타','[테스트] 각종 청소 서비스', 300, '테스트데이터, 청소 서비스 입니다.', 1);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[청소] 건물 계단 청소', 300, '테스트데이터, 청소 서비스 입니다.', 1);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[청소] 안방 청소', 300, '테스트데이터, 청소 서비스 입니다.', 1);

INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[청소] 사무실 청소 전문', 300, '테스트데이터, 청소 서비스 입니다.', 2);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('빨래','[빨래] 대형 빨래 세탁', 300, '테스트데이터, 청소 서비스 입니다.', 2);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[청소] 안방 청소', 300, '테스트데이터, 청소 서비스 입니다.', 2);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('방역','[방역] 원룸 청소', 300, '테스트데이터, 청소 서비스 입니다.', 2);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('보안','[보안] 원룸 보안', 300, '테스트데이터, 청소 서비스 입니다.', 2);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[청소] 정기 청소', 300, '테스트데이터, 청소 서비스 입니다.', 2);
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('방역','[방역] 쥐 잡이', 300, '테스트데이터, 청소 서비스 입니다.', 2);
-- ----------------------------------------------------------------------------------------------------------------

------------------------------------------- 팁게시판 게시글 샘플 ---------------------------------------------------------------
 INSERT INTO board( board_title, user_no, board_content)    -- board_no = 1
        VALUES ( "자취 꿀팁 전수!!", 4, "엄마한테 전화하기!" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "원룸 살 때 필수 아이템", 5, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "바퀴벌레 잡는데는 역시 바퀴벌레 아저씨가 짱", 6, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "빨래 하는 법..", 7, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "간단한 자취 요리", 8, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "배달 음식 처리", 9, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "너무 심심해요 ㅠㅠ", 10, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "룸메랑 같이 사시는 분 있나요?", 11, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "자취 초년생 🤭", 12, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "이사가려고 하는데,,,", 13, "다이웃 짱" );
 INSERT INTO board( board_title, user_no, board_content)
        VALUES ( "셀프 이사 하는 법", 14, "엄마를 부른다" );
 -- ----------------------------------------------------------------------------------------------------------------


------------------------------------------- 팁게시판 댓글 샘플 ---------------------------------------------------------------
 INSERT INTO reply (board_no, parent_no, user_no, reply_content)
        VALUES ( 11, 0, 5, "완전 인정 ㅠㅠ 엄마없이 못살아..");
 INSERT INTO reply (board_no, parent_no, user_no, reply_content)
        VALUES ( 11, 1, 4, "엄마 보고싶어요...");
-- ----------------------------------------------------------------------------------------------------------------
