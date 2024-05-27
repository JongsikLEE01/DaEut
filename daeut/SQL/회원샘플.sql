-- Active: 1715242304860@@127.0.0.1@3306@joeun

-- ID : downy
-- PW : 123456


-- 회원 컬럼 나열
user_no, user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_reg_date, user_coupon, user_upd_date, enabled

user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon 

-- 회원 컬럼에 매핑된 변수
#{userName}, #{userPhone}, #{userBirth}, #{userAddress}, #{userEmail}, #{userGender}, #{userId}, #{userPassword}, #{userCoupon}

-- 👩‍💼 회원
-- 샘플 데이터
-- 회원 
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('정다운','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','downy','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');


-- 회원 권한
INSERT INTO user_auth ( user_no,  auth )
VALUES ( 1, 'ROLE_USER' );


-- 👨‍🔬 파트너
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('파트너','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','partner','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 2, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 2, 'ROLE_PARTNER' );

-- 👮‍♀️ 관리자
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('관리자','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','admin','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_PARTNER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 3, 'ROLE_ADMIN' );