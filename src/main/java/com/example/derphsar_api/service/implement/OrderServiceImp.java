package com.example.derphsar_api.service.implement;

import com.example.derphsar_api.repository.OrderRepository;
import com.example.derphsar_api.repository.dto.OrderDto;
import com.example.derphsar_api.service.OrderService;
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

    //    create order
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        boolean isInserted = orderRepository.insert(orderDto);
        if (isInserted)
            return orderDto;
        else
            return null;
    }

    //    get order
    @Override
    public List<OrderDto> getOrders(){
        return orderRepository.select();
    }

    //    delete order
    @Override
    public void deleteOrder(String order_id) {
        orderRepository.delete(order_id);
    }
}
