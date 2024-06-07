-- Active: 1717757886166@@127.0.0.1@3306@joeun

-- service_no -- [order_item] --> orders_no
-- orders_no -- [payment] -->service_date

SELECT (SELECT service_name FROM service WHERE service_no = 1) title
      ,(SELECT service_content FROM service WHERE service_no = 1) description
      ,service_date start
      ,service_date end
FROM payment
WHERE orders_no IN (SELECT orders_no
                    FROM order_item
                    WHERE service_no = 1)
;

SELECT * FROM service;
SELECT * FROM payment;
SELECT * FROM order_item;



--
SELECT  s.*, 
        f.*
FROM joeun.service s    LEFT JOIN (  
                                SELECT * 
                                FROM joeun.files
                                WHERE parent_table = 'service'
                                AND file_code = 1
                                ) f
                        ON s.service_no = f.parent_no
WHERE service_no = 5;