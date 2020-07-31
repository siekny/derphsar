package com.example.derphsar_api.repository;

import com.example.derphsar_api.repository.dto.OrderDto;
import com.example.derphsar_api.repository.dto.ShopDto;
import com.example.derphsar_api.repository.provider.OrderProvider;
import com.example.derphsar_api.repository.provider.ShopProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    //    Insert shop
    @Insert("INSERT INTO dp_order(order_id, user_id, shop_id) "+
            "VALUES (#{orderId}, #{user_id}, #{shop_id})")

    boolean insert(OrderDto orderDto);


    //    Get shops
    @SelectProvider(value = OrderProvider.class, method = "getOrders")
    @Results({
            @Result(column = "order_id" ,property = "orderId")
    })
    List<OrderDto> select();


    //    Delete shop
    @Delete("delete FROM dp_order where order_id = #{order_id}")
    void delete(String order_id);

}
