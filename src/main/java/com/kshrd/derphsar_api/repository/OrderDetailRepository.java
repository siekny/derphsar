package com.kshrd.derphsar_api.repository;


import com.kshrd.derphsar_api.repository.dto.*;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository {

    //select all order details
    @Select("SELECT * FROM dp_order_detail WHERE status = 'TRUE' ")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "order_date", property = "orderDate"),
            @Result(column = "order_id", property = "order", many = @Many(select = "getOrder")),
            @Result(column = "pro_id", property = "product", many = @Many(select = "getProduct"))
    })
    List<OrderDetailDto> getOrderDetails();


    //select all order
    @Select("SELECT * FROM dp_order WHERE id = #{order_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "order_id" ,property = "orderId"),
            @Result(column = "user_id", property = "user", many = @Many(select = "getUser")),
            @Result(column = "shop_id", property = "shop", many = @Many(select = "getShop")),

    })
    OrderDto getOrder(int order_id);



    //select user
    @Select("SELECT * FROM dp_users WHERE id = #{user_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "user_id" ,property = "userId")
    })
    UserDto getUser(int user_id);




    //get all shop
    @Select("SELECT * FROM dp_shops WHERE id = #{shop_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "shop_id" ,property = "shopId"),
            @Result(column = "promo_id", property = "promotion", many = @Many(select = "getPromotion")),
    })
    ShopDto getShop(int shop_id);



    //select promotion
    @Select("SELECT * FROM dp_promotion WHERE id = #{promo_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "promo_id" ,property = "promoId"),
            @Result(column = "start_rank" ,property = "startRank"),
            @Result(column = "end_rank" ,property = "endRank"),

    })
    PromotionDto getPromotion(int promo_id);




    @Select("SELECT * FROM dp_products WHERE id = #{pro_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "pro_id" ,property = "proId")
    })
    ProductDto getProduct(int pro_id);


}
