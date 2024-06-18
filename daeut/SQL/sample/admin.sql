-- Active: 1717813530241@@127.0.0.1@3306@joeun
-- 👮‍♀️ 관리자
INSERT INTO users (user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon)
VALUES ('관리자','010-7667-2354','2024-01-01','인천시','downy@gmail.com','여자','admin','$2a$12$TrN..KcVjciCiz.5Vj96YOBljeVTTGJ9AUKmtfbGpgc9hmC7BxQ92', '');
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 1, 'ROLE_USER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 1, 'ROLE_PARTNER' );
INSERT INTO user_auth ( user_no,  auth ) VALUES ( 1, 'ROLE_ADMIN' );