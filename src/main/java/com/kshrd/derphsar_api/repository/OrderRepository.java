package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.repository.provider.OrderProvider;
import com.kshrd.derphsar_api.repository.provider.WishListProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    @SelectProvider(type = OrderProvider.class, method = "getAllOrderByShopId")
    @Results({
            @Result(property = "orderId", column = "order_id"),

            @Result(property = "user.name", column = "userName"),
            @Result(property = "user.userId", column = "user_id"),
            @Result(property = "user.phone", column = "phone"),

            @Result(property = "shop.shopId", column = "shop_id"),
            @Result(property = "shop.name", column = "shopName"),

            @Result(property = "orderDetail.itemId", column = "item_id"),
            @Result(property = "orderDetail.quatity", column = "quatity"),


    })
    List<OrderDto> getAllOrderByShopId(int shopId);


}
