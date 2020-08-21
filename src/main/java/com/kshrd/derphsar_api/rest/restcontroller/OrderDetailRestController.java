package com.kshrd.derphsar_api.rest.restcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.order.request.OrderRequest;
import com.kshrd.derphsar_api.rest.order.response.OrderResponse;
import com.kshrd.derphsar_api.rest.orderdetail.OrderDetailFirstRequest;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailFilterResponse;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.rest.shop.response.ShopCreateFirstResponse;
import com.kshrd.derphsar_api.rest.utils.BaseApiNoPaginationResponse;
import com.kshrd.derphsar_api.service.implement.OrderDetailServiceImp;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@CrossOrigin(origins = "*", maxAge = 3600)
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





    /**
     * Get order details
     *
     * @return - Return response message
     */
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


    /**
     * Get order details
     *
     * @param orderDetailFilter - Order detail filter
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("/orderdetail")
    @ApiOperation("show all orderdetails filter by userId and orderId")
    public ResponseEntity<BaseApiResponse<List<OrderDetailFilterResponse>>> FilterbyUserAndOrder(OrderDetailFilter orderDetailFilter,
                                                                                                 @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                 @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                 @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                 @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {
        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();

        pagination.setTotalCount(orderDetailServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<OrderDetailFilterResponse>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        List<OrderDetailDto> orderDetailDtos = orderDetailServiceImp.findAllWithFilter(orderDetailFilter,pagination);
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
        response.setPagination(pagination);
        response.setData(orderDetailResponseList);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setMessage(message.selected("OrderDetails"));

        return ResponseEntity.ok(response);
    }


    /**
     * Delete an order detail
     *
     * @param orderDetailId - Id of order detail
     * @return - Return response message
     */
    @DeleteMapping("/orderdetails/{orderDetailId}")
    public ResponseEntity<BaseApiNoPaginationResponse<String>> delete(@PathVariable String orderDetailId)
    {
        BaseApiNoPaginationResponse<String> response = new BaseApiNoPaginationResponse<>();
        try{
            Boolean isDeleted = orderDetailServiceImp.deleteOrderdetail(orderDetailId);
            if(isDeleted)
            {
                response.setMessage(message.deleted("OrderDetail"));
                response.setStatus(HttpStatus.OK);
            }
            else
            {
                response.setMessage(message.deleteError("OrderDetail"));
                response.setStatus(HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e)
        {
            response.setMessage(message.deleteError("OrderDetails"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }




    /**
     * Post a shop
     *
     * @param orderDetailFirstRequest - Shop request model
     * @return - Return response message
     */
    @PostMapping("/orderdetails")
    @ApiOperation(value = "add products to cart", response = OrderDetailFilterResponse.class)
    public ResponseEntity<BaseApiNoPaginationResponse<OrderDetailFilterResponse>> addProductToCart(@RequestBody OrderDetailFirstRequest orderDetailFirstRequest){

        ModelMapper mapper = new ModelMapper();
        UUID uuid = UUID.randomUUID();
        BaseApiNoPaginationResponse<OrderDetailFilterResponse> response = new BaseApiNoPaginationResponse<>();

        OrderDetailDto orderDetailDto = mapper.map(orderDetailFirstRequest,OrderDetailDto.class);

        if(orderDetailFirstRequest.getQuatity() != 0 ){
            orderDetailDto.setItemId("DP"+uuid.toString().substring(0,10));
            orderDetailDto.setStatus(true);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Date date = new Date(timestamp.getTime());
           // System.out.println(date);

            orderDetailDto.setOrderDate(date);
            OrderDetailDto orderDetailDto1 = orderDetailServiceImp.addProductToCart(orderDetailDto);

            OrderDetailFilterResponse orderDetailFilterResponse = mapper.map(orderDetailDto1, OrderDetailFilterResponse.class);
            response.setMessage(message.inserted("OrderDetail"));
            response.setData(orderDetailFilterResponse);
            response.setStatus(HttpStatus.CREATED);

        }else {
            response.setMessage(message.insertError("OrderDetail"));
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
