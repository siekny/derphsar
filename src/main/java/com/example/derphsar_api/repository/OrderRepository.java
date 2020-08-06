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

    //Insert order
    @Insert("INSERT INTO dp_order(order_id, user_id, shop_id) "+
            "VALUES (#{orderId}, #{user_id}, #{shop_id})")

    boolean insert(OrderDto orderDto);


    //Get order
    @Select("SELECT dp_order.shop_id,dp_order.user_id, dp_order_detail.item_id, dp_order_detail.detail, dp_order_detail.quantity, dp_order_detail.order_date, dp_order_detail.status, dp_order_detail.order_id, dp_order_detail.pro_id\n" +
            "FROM dp_order_detail \n" +
            "INNER JOIN dp_order ON dp_order_detail.order_id = dp_order.id;")
    @Results({
            @Result(column = "order_id" ,property = "orderId")
    })
    List<OrderDto> select();


    //Delete order
    @Delete("DELETE FROM dp_order where order_id = #{order_id}")
    void delete(String order_id);

}
