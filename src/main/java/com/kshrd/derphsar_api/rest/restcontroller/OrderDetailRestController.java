package com.kshrd.derphsar_api.rest.restcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailFilterResponse;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.service.implement.OrderDetailServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailRestController {
    private OrderDetailServiceImp orderDetailServiceImp;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setOrderDetailServiceImp(OrderDetailServiceImp orderDetailServiceImp) {
        this.orderDetailServiceImp = orderDetailServiceImp;
    }

    //get all orderDetails
    @GetMapping("/orderdetails")
    @ApiOperation(value = "show all order details", response = OrderDetailResponse.class)
    public ResponseEntity<BaseApiResponse<List<OrderDetailResponse>>> getOrderDetails(){

        BaseApiResponse<List<OrderDetailResponse>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        List<OrderDetailDto> orderDetailDtos = orderDetailServiceImp.getOrderDetails();
        List<OrderDetailResponse> orderDetailResponseList = new ArrayList<>();


            for(OrderDetailDto orderDetailDto : orderDetailDtos){
                try{
                    Object detail = mapper.readValue(orderDetailDto.getDetail().toString(), Object.class);
                    Object image = mapper.readValue(orderDetailDto.getImage().toString(), Object.class);

                    orderDetailDto.setDetail(detail);
                    orderDetailDto.setImage(image);

                    ModelMapper modelMapper = new ModelMapper();
                    OrderDetailResponse orderDetailResponse = modelMapper.map(orderDetailDto, OrderDetailResponse.class);
                    orderDetailResponseList.add(orderDetailResponse);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }
            response.setData(orderDetailResponseList);
            response.setStatus(HttpStatus.FOUND);
            response.setTime(new Timestamp(System.currentTimeMillis()));
            response.setMessage(message.selected("OrderDetails"));


        return ResponseEntity.ok(response);

    }



    @GetMapping("/orderdetail")
    @ApiOperation("show all orderdetails filter by userId and orderId")
    public ResponseEntity<BaseApiResponse<List<OrderDetailFilterResponse>>> FilterbyUserAndOrder(OrderDetailFilter orderDetailFilter) {

        BaseApiResponse<List<OrderDetailFilterResponse>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        List<OrderDetailDto> orderDetailDtos = orderDetailServiceImp.findAllWithFilter(orderDetailFilter);
        List<OrderDetailFilterResponse> orderDetailResponseList = new ArrayList<>();


        for(OrderDetailDto orderDetailDto : orderDetailDtos){
            try{
                Object detail = mapper.readValue(orderDetailDto.getDetail().toString(), Object.class);
                Object image = mapper.readValue(orderDetailDto.getImage().toString(), Object.class);

                orderDetailDto.setDetail(detail);
                orderDetailDto.setImage(image);

                ModelMapper modelMapper = new ModelMapper();
                OrderDetailFilterResponse orderDetailResponse = modelMapper.map(orderDetailDto, OrderDetailFilterResponse.class);
                orderDetailResponseList.add(orderDetailResponse);
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
        response.setData(orderDetailResponseList);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setMessage(message.selected("OrderDetails"));

        return ResponseEntity.ok(response);
    }

}
