package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class OrderProvider {

    public String getAllOrderByShopId(int shopId){
        return new SQL(){{
            SELECT("DISTINCT(o.order_id) , u.name AS userName, u.user_id, u.phone, sh.shop_id, sh.name AS shopName, ord.item_id, ord.quatity, ord.order_date");
            FROM("dp_order AS o");
            INNER_JOIN("dp_users AS u ON u.id = o.user_id");
            INNER_JOIN("dp_shops AS sh ON sh.id = o.shop_id");
            INNER_JOIN("dp_order_detail AS ord ON ord.order_id = o.id");
            WHERE("o.shop_id = #{shopId}");

        }}.toString();
    }

    public String orderDetailByOrderId(int orderId) {
        return new SQL(){{
            SELECT("item_id, quatity, order_date");
            FROM("dp_order_detail");
            WHERE("order_id = #{orderId}");
        }}.toString();
    }
}
