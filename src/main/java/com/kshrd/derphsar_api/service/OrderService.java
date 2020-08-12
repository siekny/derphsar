package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrderByShopId(int shopId);
    List<OrderDto> getOrdersLatestFiveRecords();
}
