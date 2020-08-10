package com.kshrd.derphsar_api.service.implement;


import com.kshrd.derphsar_api.repository.OrderDetailRepository;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.service.OrderDetailService;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImp implements OrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetailDto> getOrderDetails(){
        List<OrderDetailDto> data = orderDetailRepository.getOrderDetails();
        try{
          if(!data.isEmpty())
              return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
