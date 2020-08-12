package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.OrderRepository;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImp implements OrderService {

    private OrderRepository orderRepository;


    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getAllOrderByShopId(int shopId) {
        //System.out.println( orderRepository.getSumQty(6));

        return  orderRepository.getAllOrderByShopId(shopId);
    }

    @Override
    public List<OrderDto> getOrdersLatestFiveRecords() {
        return orderRepository.getOrdersLatestFiveRecords();
    }
}
