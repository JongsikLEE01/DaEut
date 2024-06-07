-- 결제 관련 테이블
-- user : 유저
-- user_name, user_phone, user_birth, user_address, user_email, user_gender, user_id, user_password, user_coupon 
-- user_auth : 유저 권한
-- auth_no, auth, user_no

-- payment  : 결제
-- payment_no, payment_method, payment_price, reservation_no

-- reservation : 예약
-- reservation_no, reservation_status, reservation_time, user_no, service_no, partner_no

-- service : 상품
-- service_no, service_category, service_name, service_price, service_content, partner_no

INSERT INTO payment
VALUES (1, 'card', 'OK', '2024-06-06', '2024-06-06', '2024-06-06', 'ORD123456', '2024-06-06');