package com.example.derphsar_api.service;

import com.example.derphsar_api.repository.dto.OrderDto;
import com.example.derphsar_api.repository.dto.ShopDto;

import java.util.List;

public interface OrderService {

    //    create order
    OrderDto createOrder(OrderDto orderDto);

    //    get all order
    List<OrderDto> getOrders();

    //    delete order
    void deleteOrder(String order_id);
}
