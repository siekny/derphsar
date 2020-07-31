package com.example.derphsar_api.rest.order.restcontroller;

import com.example.derphsar_api.repository.dto.OrderDto;
import com.example.derphsar_api.rest.BaseApiResponse;
import com.example.derphsar_api.rest.order.request.OrderRequestModel;
import com.example.derphsar_api.service.implement.OrderServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {

    private OrderServiceImp orderServiceImp;

    @Autowired
    public void setOrderServiceImp(OrderServiceImp orderServiceImp) {
        this.orderServiceImp = orderServiceImp;
    }

    //    create a order
    @PostMapping("/orders")
    public ResponseEntity<BaseApiResponse<OrderRequestModel>> createOrder(@RequestBody OrderRequestModel orderRequestModel){

        BaseApiResponse<OrderRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        OrderDto orderDto = mapper.map(orderRequestModel, OrderDto.class);


        UUID uuid = UUID.randomUUID();
        orderDto.setOrderId("DP"+uuid.toString().substring(0,10));

        OrderDto result = orderServiceImp.createOrder(orderDto);
        OrderRequestModel requestModel = mapper.map(result, OrderRequestModel.class);

        response.setMessage("you have inserted an order successfully!");
        response.setData(requestModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


//    get all orders
    @GetMapping("/orders")
    public ResponseEntity<BaseApiResponse<List<OrderRequestModel>>> getOrder() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<OrderRequestModel>> response =
                new BaseApiResponse<>();

        List<OrderDto> orderDtos = orderServiceImp.getOrders();
        List<OrderRequestModel> models = new ArrayList<>();
        for (OrderDto orderDto : orderDtos) {
            models.add(mapper.map(orderDto, OrderRequestModel.class));
        }
        response.setMessage("you have selected all orders successfully!");
        response.setData(models);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

//    delete a order
    @DeleteMapping("/orders/{order_id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteOrder(@PathVariable("order_id") String order_id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        orderServiceImp.deleteOrder(order_id);
        response.setMessage("you have deleted an order successfully!");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
