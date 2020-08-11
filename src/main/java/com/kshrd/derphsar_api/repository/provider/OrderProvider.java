package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class OrderProvider {

    public String getAllOrderByShopId(int shopId){
        return new SQL(){{
            SELECT("o.order_id, u.name AS userName, u.user_id, u.phone, sh.shop_id, sh.name AS shopName, ord.item_id, ord.quatity");
            FROM("dp_order AS o");
            INNER_JOIN("dp_users AS u ON u.id = o.user_id");
            INNER_JOIN("dp_shops AS sh ON sh.id = o.shop_id");
            INNER_JOIN("dp_order_detail AS ord ON ord.order_id = o.id");
//            INNER_JOIN("dp_products AS pro ON pro.id = ord.pro_id");
            WHERE("o.shop_id = #{shopId}");

        }}.toString();
    }
}
