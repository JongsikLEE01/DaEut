-- 파트너
-- partner, 123456
-- 유저
-- jslee, 123456

-- service : 상품
-- service_category, service_name, service_price, service_content, partner_no, reg_date, upd_date

-- TRUNCATE TABLE service;
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[테스트] 청소', 300, '테스트데이터, 청소 서비스 입니다.', 1);

-- reservation 샘플데이터(Order)
INSERT INTO orders (user_no, partner_no, order_status, orders_no, total_quantity, total_price, total_count) 
VALUES 
(2, 3, 'Confirmed', 'ORD123456', 3, 50000, 3),
(2, 3, 'Pending', 'ORD789012', 2, 30000, 2),
(2, 3, 'Cancelled', 'ORD345678', 1, 15000, 1);