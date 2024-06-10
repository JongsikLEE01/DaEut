-- Active: 1717757886166@@127.0.0.1@3306@joeun

-- partner
TRUNCATE partner;
INSERT INTO partner (partner_grade, partner_reserve, partner_career, introduce, user_no)
VALUES
( 5, 10, '10년 경력', '안녕하세요, 저는 파트너입니다.', 3),
( 4, 8, '8년 경력', '안녕하세요, 파트너입니다.', 3),
( 3, 6, '6년 경력', '안녕하세요, 파트너입니다.', 3),
( 4, 8, '8년 경력', '안녕하세요, 저는 파트너입니다.', 3),
( 5, 10, '10년 경력', '안녕하세요, 저는 파트너입니다.', 3),
( 3, 6, '6년 경력', '안녕하세요, 파트너입니다.', 3),
( 4, 8, '8년 경력', '안녕하세요, 저는 파트너입니다.', 3),
( 5, 10, '10년 경력', '안녕하세요, 파트너입니다.', 3),
( 3, 6, '6년 경력', '안녕하세요, 저는 파트너입니다.', 3),
( 4, 8, '8년 경력', '안녕하세요, 파트너입니다.', 3);


-- orders
-- PENDING, PAID, CONFIRMED, CANCELLED
TRUNCATE orders;
INSERT INTO orders (orders_no, user_no, order_status, total_quantity, total_price, total_count, title)
VALUES
('ORD1', 1, 'PAID', 2, 20000, 2, '예약 제목 1'),
('ORD2', 1, 'PENDING', 3, 30000, 3, '예약 제목 2'),
('ORD3', 1, 'PAID', 1, 15000, 1, '예약 제목 3'),
('ORD4', 1, 'PENDING', 4, 45000, 4, '예약 제목 4'),
('ORD5', 1, 'PENDING', 2, 25000, 2, '예약 제목 5'),
('ORD6', 1, 'PAID', 3, 35000, 3, '예약 제목 6'),
('ORD7', 1, 'PENDING', 1, 10000, 1, '예약 제목 7'),
('ORD8', 1, 'PAID', 5, 50000, 5, '예약 제목 8'),
('ORD9', 1, 'PENDING', 2, 20000, 2, '예약 제목 9'),
('ORD10', 1, 'PENDING', 3, 30000, 3, '예약 제목 10');

--
TRUNCATE order_item;
INSERT INTO order_item (item_no, quantity, price, amount, upd_date, reg_date, orders_no, service_no)
VALUES
('ITEM1', 2, 10000, 20000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD1', 1),
('ITEM2', 1, 15000, 15000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD1', 2),
('ITEM3', 3, 10000, 30000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD2', 1),
('ITEM4', 1, 10000, 10000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD3', 1),
('ITEM5', 4, 11250, 45000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD4', 2),
('ITEM6', 2, 12500, 25000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD5', 1),
('ITEM7', 3, 11666, 35000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD6', 2),
('ITEM8', 1, 10000, 10000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD7', 1),
('ITEM9', 5, 10000, 50000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD8', 2),
('ITEM10', 2, 10000, 20000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD9', 1);



-- payment
-- PENDING, PAID, REFUND
TRUNCATE payment;
INSERT INTO payment (payment_no, payment_method, status, pay_date, reg_date, upd_date, orders_no, service_date)
VALUES
(1, '신용카드', 'PAID', '2024-06-15 10:30:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD1', '2024-06-15 10:30:00'),
(2, '현금', 'PENDING', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD2', '2024-06-20 09:00:00'),
(3, '신용카드', 'PAID', '2024-06-25 14:45:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD3', '2024-06-25 14:45:00'),
(4, '계좌이체', 'PENDING', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD4', '2024-06-30 11:20:00'),
(5, '신용카드', 'PENDING', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD5', '2024-07-05 15:00:00'),
(6, '현금', 'PAID', '2024-07-10 13:10:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD6', '2024-07-10 13:10:00'),
(7, '계좌이체', 'PENDING', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD7', '2024-07-15 16:30:00'),
(8, '신용카드', 'PAID', '2024-07-20 09:45:00', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD8', '2024-07-20 09:45:00'),
(9, '현금', 'PENDING', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD9', '2024-07-25 12:00:00'),
(10, '신용카드', 'PENDING', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ORD10', '2024-07-30 14:20:00');



-- service
TRUNCATE service;
INSERT INTO service (service_category, service_name, service_price, service_content, upd_date, reg_date, partner_no)
VALUES
('청소', '정기 청소', 50, '기본적인 가정 또는 사무 공간의 청소 서비스입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('가드닝', '잔디 관리', 80, '우리 팀은 잔디를 깎고, 다듬고, 잔등을 처리합니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('배관 공사', '파이프 수리', 120, '누수나 손상된 파이프에 대한 전문 수리 서비스를 제공합니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('전기 공사', '조명 설치', 100, '내부 또는 외부 조명기구의 설치 서비스를 우리의 숙련된 전기 기사들이 제공합니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('도배', '실내 도배', 150, '전문적인 도배 서비스로 내부 공간을 변형합니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('이사', '지역 이사 서비스', 200, '저희 이사팀이 도움을 드립니다. 도시 또는 지역 내 이사.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('목공 공사', '가구 조립', 80, '침대, 테이블, 의자 등의 가구 조립 서비스입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('난방 및 냉방 공사', '에어컨 수리', 180, '주거용 및 상업용 에어컨 시스템의 수리 및 유지보수 서비스입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('지붕 공사', '지붕 수리', 250, '손상된 또는 누수된 지붕에 대한 전문 수리 서비스입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('해충 방제', '방충제 제거', 120, '귀하의 부동산을 보호하기 위한 효과적인 방충제 제거 서비스입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3);


--
--
TRUNCATE files;
INSERT INTO files (parent_table, parent_no, file_name, origin_file_name, file_path, file_size, file_code)
VALUES
('service', 1, 'cleaning_image1.jpg', 'cleaning_image1.jpg', '/path/to/cleaning_image1.jpg', 1024, 1),
('service', 2, 'gardening_image1.jpg', 'gardening_image1.jpg', '/path/to/gardening_image1.jpg', 2048, 1),
('service', 3, 'plumbing_image1.jpg', 'plumbing_image1.jpg', '/path/to/plumbing_image1.jpg', 3072, 1),
('service', 4, 'electrical_image1.jpg', 'electrical_image1.jpg', '/path/to/electrical_image1.jpg', 4096, 1),
('service', 5, 'painting_image1.jpg', 'painting_image1.jpg', '/path/to/painting_image1.jpg', 5120, 1),
('service', 6, 'moving_image1.jpg', 'moving_image1.jpg', '/path/to/moving_image1.jpg', 6144, 1),
('service', 7, 'carpentry_image1.jpg', 'carpentry_image1.jpg', '/path/to/carpentry_image1.jpg', 7168, 1),
('service', 8, 'hvac_image1.jpg', 'hvac_image1.jpg', '/path/to/hvac_image1.jpg', 8192, 1),
('service', 9, 'roofing_image1.jpg', 'roofing_image1.jpg', '/path/to/roofing_image1.jpg', 9216, 1),
('service', 10, 'pest_control_image1.jpg', 'pest_control_image1.jpg', '/path/to/pest_control_image1.jpg', 10240, 1),
('service', 1, 'cleaning_image1.jpg', 'cleaning_image1.jpg', '/path/to/cleaning_image1.jpg', 1024, 0),
('service', 2, 'gardening_image1.jpg', 'gardening_image1.jpg', '/path/to/gardening_image1.jpg', 2048, 0),
('service', 3, 'plumbing_image1.jpg', 'plumbing_image1.jpg', '/path/to/plumbing_image1.jpg', 3072, 0),
('service', 4, 'electrical_image1.jpg', 'electrical_image1.jpg', '/path/to/electrical_image1.jpg', 4096, 0),
('service', 5, 'painting_image1.jpg', 'painting_image1.jpg', '/path/to/painting_image1.jpg', 5120, 0),
('service', 6, 'moving_image1.jpg', 'moving_image1.jpg', '/path/to/moving_image1.jpg', 6144, 0),
('service', 7, 'carpentry_image1.jpg', 'carpentry_image1.jpg', '/path/to/carpentry_image1.jpg', 7168, 0),
('service', 8, 'hvac_image1.jpg', 'hvac_image1.jpg', '/path/to/hvac_image1.jpg', 8192, 0),
('service', 9, 'roofing_image1.jpg', 'roofing_image1.jpg', '/path/to/roofing_image1.jpg', 9216, 0),
('service', 10, 'pest_control_image1.jpg', 'pest_control_image1.jpg', '/path/to/pest_control_image1.jpg', 10240,0)
;


--
SELECT  s.*, 
        		f.*
        FROM joeun.service s    LEFT JOIN (  
        	                    	    SELECT * 
        	                    	    FROM joeun.files
                                        WHERE parent_table = 'service'
                                        AND file_code != 1
        	                    	    ) f
        	                    ON s.service_no = f.parent_no
        WHERE service_no = 5
        ;