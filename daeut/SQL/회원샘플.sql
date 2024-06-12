-- Active: 1717757886166@@127.0.0.1@3306@joeun

-- ID : downy
-- PW : 123456


-- 회원 컬럼 나열
-- user_no, user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_reg_date, user_coupon, user_upd_date, enabled
-- user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon 

-- 회원 컬럼에 매핑된 변수
#{userName}, #{userPhone}, #{userBirth}, #{userAddress}, #{userEmail}, #{userGender}, #{userId}, #{userPassword}, #{userCoupon}


TRUNCATE users;
TRUNCATE user_auth;
-- 👩‍💼 회원
-- 샘플 데이터
-- 회원 
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('정다운','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','downy','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');

-- 👩‍💼 회원
-- 샘플 데이터
-- 회원 
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('이종식','010-7667-2354','2024-01-01','인천시','jslee@gmail.com','남자','jslee','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');

-- 회원 권한
INSERT INTO user_auth ( user_no,  auth )
VALUES ( 1, 'ROLE_USER' );
-- 회원 권한
INSERT INTO user_auth ( user_no,  auth )
VALUES ( 2, 'ROLE_USER' );


-- 👨‍🔬 파트너
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('파트너','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','partner','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_PARTNER' );

-- 👮‍♀️ 관리자
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('관리자','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','admin','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 4, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 4, 'ROLE_PARTNER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 4, 'ROLE_ADMIN' );

INSERT INTO user_auth (user_no, 권한_컬럼1, 권한_컬럼2, ...)
VALUES (3, 'ROLE_USER', 'ROLE_PARTNER', 'ROLE_ADMIN');
-- 파트너 샘플 데이터
INSERT INTO partner (partner_grade, partner_reserve, partner_career, introduce, user_no) VALUES (4, 5, "특수 청소 2년", '테스트 파트너입니다.', 3);
