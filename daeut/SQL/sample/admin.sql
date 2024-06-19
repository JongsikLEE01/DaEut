-- Active: 1717979949380@@127.0.0.1@3306@joeun
-- 👮‍♀️ 관리자
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('관리자','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','admin','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 1, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 1, 'ROLE_PARTNER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 1, 'ROLE_ADMIN' );

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
VALUES ( 2, 'ROLE_USER' );
-- 회원 권한
INSERT INTO user_auth ( user_no,  auth )
VALUES ( 3, 'ROLE_USER' );

-- 👩‍💼 회원
-- 샘플 데이터
-- 회원 
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('이종식','010-7667-2354','2024-01-01','인천시','jslee@gmail.com','남자','jslee','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');