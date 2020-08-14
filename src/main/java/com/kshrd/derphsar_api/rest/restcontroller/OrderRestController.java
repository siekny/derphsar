package com.kshrd.derphsar_api.rest.restcontroller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.order.response.OrderHistoryOfAUserResponse;
import com.kshrd.derphsar_api.rest.order.response.OrderResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductsOfAUserResponse;
import com.kshrd.derphsar_api.service.implement.OrderServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private OrderServiceImp orderServiceImp;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }


    @Autowired
    public void setOrderServiceImp(OrderServiceImp orderServiceImp) {
        this.orderServiceImp = orderServiceImp;
    }





    /**
     * get all orders by shop id
     *
     * @param shopId -  id of shop
     * @return - list of orders
     */

    @GetMapping("orders/{shopId}")
    @ApiOperation(value = "show all orders by shopId", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<OrderResponse>>> getAllOrderByShopId(@PathVariable("shopId") int shopId) {

        BaseApiResponse<List<OrderResponse>> baseApiResponse = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<OrderResponse> orderResponses = new ArrayList<>();

        try{
            List<OrderDto> orderDtos = orderServiceImp.getAllOrderByShopId(shopId);
            for (OrderDto wishListDto : orderDtos) {
                OrderResponse wishListResponse = mapper.map(wishListDto, OrderResponse.class);
                orderResponses.add(wishListResponse);
            }

            if(!orderDtos.isEmpty()){
                baseApiResponse.setMessage(message.selected("Orders"));
                baseApiResponse.setData(orderResponses);
                baseApiResponse.setStatus(HttpStatus.FOUND);
            }else {
                baseApiResponse.setMessage(message.inserted("Orders"));
                baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(baseApiResponse);
    }



    /**
     * show all latest 5 records of orders
     *
     * @return - list of orders
     */
    @GetMapping("orders")
    @ApiOperation(value = "show all recent 5 records of orders", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<OrderResponse>>> getOrdersLatestFiveRecords() {

        BaseApiResponse<List<OrderResponse>> baseApiResponse = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<OrderResponse> orderResponses = new ArrayList<>();

        try{
            List<OrderDto> orderDtos = orderServiceImp.getOrdersLatestFiveRecords();
            for (OrderDto wishListDto : orderDtos) {
                OrderResponse wishListResponse = mapper.map(wishListDto, OrderResponse.class);
                orderResponses.add(wishListResponse);
            }

            if(!orderDtos.isEmpty()){
                baseApiResponse.setMessage(message.selected("Orders"));
                baseApiResponse.setData(orderResponses);
                baseApiResponse.setStatus(HttpStatus.FOUND);
            }else {
                baseApiResponse.setMessage(message.inserted("Orders"));
                baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(baseApiResponse);
    }





    /**
     * Get Order History
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("order-history/{userId}")
    @ApiOperation(value = "show all order history by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<OrderHistoryOfAUserResponse>>> getAllOrdersHistoryByUserId(@PathVariable("userId") int userId,
                                                                                                          @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                          @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                          @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                          @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();
        pagination.setTotalCount(orderServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<OrderHistoryOfAUserResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<OrderHistoryOfAUserResponse> productResponseModels = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<OrderDto> orders = orderServiceImp.getAllOrdersHistoryByUserId(userId,pagination);
            for (OrderDto orderDto : orders) {

//                Object images = objectMapper.readValue(orderDto.getImage().toString(), Object.class);
//                orderDto.setImage(images);

                OrderHistoryOfAUserResponse productResponseModel = mapper.map(orderDto, OrderHistoryOfAUserResponse.class);
                productResponseModels.add(productResponseModel);
            }

            if (!orders.isEmpty()) {
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
                response.setMessage(message.selected("Orders"));
            }else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage(message.hasNoRecords("Orders"));
            }

            response.setPagination(pagination);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
