package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class OrderDetailProvider {

    public String findAllWithFilter(@Param("filter")OrderDetailFilter orderDetailFilter){
        return new SQL(){{
            SELECT("od.* , o.order_id AS orderId, pro.id, pro.pro_id AS proId, pro.name AS proName, pro.price, pro.is_sold, u.user_id AS userId, u.name AS userName, u.phone, sh.shop_id AS shopId, sh.name AS shopName, promo.promo_id AS promoId, promo.title, promo.start_rank, promo.end_rank");
            FROM("dp_order_detail as od");
            INNER_JOIN("dp_order as o ON o.id = od.order_id ");
            INNER_JOIN("dp_products as pro ON od.pro_id = pro.id");
            INNER_JOIN("dp_users as u ON u.id = o.user_id");
            INNER_JOIN("dp_shops as sh ON sh.id = o.shop_id");
            INNER_JOIN("dp_promotion as promo ON promo.id = sh.promo_id");
            if (orderDetailFilter.getOrderId() != null && orderDetailFilter.getUserId() != null)
                WHERE("o.user_id =  #{filter.userId} AND  o.id = #{filter.orderId}");
        }}.toString();
    }

}

