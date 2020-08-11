package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.repository.provider.OrderProvider;
import com.kshrd.derphsar_api.repository.provider.WishListProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    //@SelectProvider(type = OrderProvider.class, method = "getAllOrderByShopId")
    @Select("SELECT DISTINCT( o.order_id),u.user_id, u.name AS userName, u.phone, sh.shop_id, sh.name AS shopName,ord.status,ord.is_checkout, ord.order_date\n" +
            "            FROM dp_order AS o\n" +
            "            INNER JOIN dp_users AS u ON u.id = o.user_id\n" +
            "            INNER JOIN dp_shops AS sh ON sh.id = o.shop_id\n" +
            "            INNER JOIN dp_order_detail AS ord ON ord.order_id = o.id\n" +
            "\t\t\t\t\t\tWHERE o.shop_id = #{shopId} AND ord.is_checkout = 'TRUE' ")

//    @Select("SELECT o.order_id, u.name AS userName, u.phone, sh.shop_id, sh.name AS shopName, ord.item_id, ord.order_id AS item_order_id, ord.order_date\n" +
//            "            FROM dp_order AS o\n" +
//            "            INNER JOIN dp_users AS u ON u.id = o.user_id\n" +
//            "            INNER JOIN dp_shops AS sh ON sh.id = o.shop_id\n" +
//            "            INNER JOIN dp_order_detail AS ord ON ord.order_id = o.id\n" +
//            "\t\t\t\t\t\tWHERE o.shop_id = 2")
    @Results({
            @Result(property = "orderId", column = "order_id"),

            @Result(property = "user.name", column = "userName"),
            @Result(property = "user.userId", column = "user_id"),
            @Result(property = "user.phone", column = "phone"),

            @Result(property = "shop.shopId", column = "shop_id"),
            @Result(property = "shop.name", column = "shopName"),

            //@Result(property = "orderDetail.itemId", column = "item_id"),
//            @Result(property = "orderDetail", column = "item_order_id", many = @Many(select = "getAllOrderDetailByOrderId")),
            @Result(property = "orderDetail.checkoutStatus", column = "is_checkout"),
            @Result(property = "orderDetail.status", column = "status"),
            @Result(property = "orderDetail.orderDate", column = "order_date"),


    })
    List<OrderDto> getAllOrderByShopId(int shopId);


    @SelectProvider(type = OrderProvider.class, method = "orderDetailByOrderId")
    @Results({
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "quatity", column = "quatity"),
            @Result(property = "orderDate", column = "order_date"),
    })
    List<OrderDetailDto> getAllOrderDetailByOrderId(int orderId);
}
