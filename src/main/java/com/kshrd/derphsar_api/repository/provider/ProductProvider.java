package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class ProductProvider {

    //get all products
//    public String getProducts() {
//        return new SQL(){{
//            SELECT("id, pro_id, pro_name, pro_price, pro_description, pro_status, pro_is_sold, pro_view_count,\n" +
//                    "pro_img, pro_details ->> 'color' AS color, pro_details ->> 'size' AS size, shop_id");
//            FROM("dp_products");
//        }}.toString();
//    }

    public String getProducts() {
        return new SQL(){{
            SELECT("*");
            FROM("dp_products");
        }}.toString();
    }



}
