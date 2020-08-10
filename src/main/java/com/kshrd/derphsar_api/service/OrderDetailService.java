package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;

import java.util.List;

public interface OrderDetailService {

    //get all orderDetail
    List<OrderDetailDto> getOrderDetails();

    List<OrderDetailDto> findAllWithFilter(OrderDetailFilter orderDetailFilter);
}
