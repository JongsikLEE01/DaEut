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
INSERT INTO `joeun`.`orders` (`orders_no`, `user_no`, `order_status`, `total_quantity`, `total_price`, `upd_date`, `reg_date`, `total_count`, `title`) 
VALUES ('ORD123456', '5', 'CONFIRMED', '1', '50000', '2024-06-07', '2024-06-07', '1', '서비스이용');
