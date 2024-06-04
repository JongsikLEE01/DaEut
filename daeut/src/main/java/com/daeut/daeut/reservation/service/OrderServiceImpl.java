package com.daeut.daeut.reservation.service;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.dto.OrderItems;
import com.daeut.daeut.reservation.dto.OrderStatus;
import com.daeut.daeut.reservation.dto.Orders;
import com.daeut.daeut.reservation.dto.Services;
import com.daeut.daeut.reservation.mapper.OrderMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private OrderItemService  orderItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PartnerService partnerService;

    @Override
    public List<Orders> list() throws Exception {
        List<Orders> ordersList = orderMapper.list();
        for (Orders order : ordersList) {
            Users user = userService.selectByUserNo(order.getUserNo()); 
            order.setUser(user);
        }
        return ordersList;
    }

    @Override
    public Orders select(String ordersNo) throws Exception {
        Orders order = orderMapper.select(ordersNo);
        int userNo = order.getUserNo();
        log.info("::::::::::: orders ~ user :::::::::::");
        log.info(" userNo : " + userNo);
        Users user = userService.selectByUserNo(userNo);
        log.info(user.toString());
        order.setUser(user);
        return order;
    }

    @Override
    public int insert(Orders orders) throws Exception {
        List<String> serviceNoList = orders.getServiceNo();
        List<Integer> quantityList = orders.getQuantity();

        if(serviceNoList == null) return 0;
        if(quantityList == null) return 0;
        if(serviceNoList.size() != quantityList.size()) return 0;

        String orderNo = UUID.randomUUID().toString();
        orders.setOrdersNo(orderNo);

        int totalCount = serviceNoList.size();
        int totalQuantity = 0;
        int totalPrice = 0;
        String title = "";

        List<OrderItems> orderItemList = new ArrayList<>();
        for(int i = 0; i < serviceNoList.size(); i++){
            String strServiceNo = serviceNoList.get(i);
            int serviceNo = Integer.parseInt(strServiceNo);
            Services service = reservationService.serviceSelect(serviceNo);
            if(i == 0) title = service.getServiceName();
            if(service == null) continue;
            OrderItems orderItem = new OrderItems();
            orderItem.setItemNo(UUID.randomUUID().toString());
            orderItem.setOrdersNo(orderNo);
            orderItem.setServiceNo(serviceNo);
            int quantity = quantityList.get(i);
            int price = service.getServicePrice();
            int amount = price * quantity;
            totalPrice += amount;
            totalQuantity += quantity;
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        title += " 외 " + totalCount + "종";

        orders.setTitle(title);
        orders.setTotalPrice(totalPrice);
        orders.setTotalQuantity(totalQuantity);
        orders.setTotalCount(totalCount);
        orders.setOrderStatus(OrderStatus.PENDING);

        // 주문 등록
        int result = orderMapper.insert(orders);

        if(result > 0){
            // 주문 항목 등록
            for (OrderItems orderItems : orderItemList) {
                orderItemService.insert(orderItems);
            }
        }

        return result;
    }

    @Override
    public int update(Orders orders) throws Exception {
        int result = orderMapper.update(orders);
        return result;
    }

    @Override
    public int delete(String ordersNo) throws Exception {
       int result = orderMapper.delete(ordersNo);
       return result;
    }

    @Override
    public List<Orders> listByUserNo(int userNo) throws Exception {
        List<Orders> orderList = orderMapper.listByUserNo(userNo);
        for (Orders order : orderList) {
            Users user = userService.selectByUserNo(order.getUserNo());
            order.setUser(user);
        }
        return orderList;
    }

    @Override
    public List<Orders> listByParterNo(int partnerNo) throws Exception {
        List<Orders> orderList = orderMapper.listByParterNo(partnerNo);
        for (Orders order : orderList) {
            Partner partner = partnerService.selectByPartnerNo(order.getPartnerNo());
            order.setPartner(partner);
        }
        return orderList;
    }

    @Override
    public Orders listByOrderNo(String orderNo) throws Exception {
        return orderMapper.listByOrderNo(orderNo);
    }
    
}
