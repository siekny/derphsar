package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.page.Pagination;
import org.apache.ibatis.annotations.Param;
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


    //get all products
    public String getProducts(@Param("pagination") Pagination pagination) {
        return new SQL(){{
            SELECT("*");
            FROM("dp_products");
            WHERE("status = TRUE");
            LIMIT("#{pagination.limit}  OFFSET #{pagination.offset}");
        }}.toString();
    }


    public String countId(){
        return new SQL(){{
            SELECT("COUNT(id)");
            FROM("dp_products");
            WHERE("status = 'TRUE'");
        }}.toString();
    }



}
