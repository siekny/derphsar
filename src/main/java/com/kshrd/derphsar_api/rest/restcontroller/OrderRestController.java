package com.kshrd.derphsar_api.rest.restcontroller;


import com.kshrd.derphsar_api.repository.OrderRepository;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.order.response.OrderResponse;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListResponse;
import com.kshrd.derphsar_api.service.implement.OrderServiceImp;
import com.kshrd.derphsar_api.service.implement.WishListServiceImp;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
    @ApiOperation(value = "show all order by shopId", response = Void.class)
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
                baseApiResponse.setMessage(message.hasNoRecords("Orders"));
                baseApiResponse.setStatus(HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        baseApiResponse.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(baseApiResponse);
    }

}
