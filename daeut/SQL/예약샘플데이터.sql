-- 파트너
-- partner, 123456
-- 유저
-- jslee, 123456

-- service : 상품
-- service_category, service_name, service_price, service_content, partner_no, reg_date, upd_date

-- TRUNCATE TABLE service;
INSERT INTO service (service_category, service_name, service_price, service_content, partner_no)
VALUES ('청소','[테스트] 청소', 300, '테스트데이터, 청소 서비스 입니다.', 3);