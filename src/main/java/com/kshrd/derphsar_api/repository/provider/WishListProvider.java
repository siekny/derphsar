package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import org.apache.ibatis.jdbc.SQL;

public class WishListProvider {

    //get all wishlists
    public String getWishList() {
        return new SQL(){{
            SELECT("*");
            FROM("dp_wishlist");
        }}.toString();
    }

    //insert new wishlist
    public String insertWishList(WishListDto wishListDto){
        return new SQL(){{
            INSERT_INTO("dp_wishlist");
            VALUES("wishlist_id, fav_date, u_id, pro_id, status", "#{wishlistId}, #{favDate}, #{u_id}, #{pro_id}, TRUE");
        }}.toString();
    }





    //
    public String selectAllWishListByUserId(){
        return new SQL(){{
            SELECT("pro.name, sh.name, pro.price, promote.start_rank, promote.end_rank, w.fav_date");
            FROM("dp_wishlist w");
            INNER_JOIN("dp_products pro ON pro.id = w.pro_id");
            INNER_JOIN("dp_shops sh ON pro.shop_id = sh.id");
            INNER_JOIN("dp_promotion promote ON promote.shop_id = sh.id");
            WHERE("u_id = #{id} AND status = 'TRUE'");
        }}.toString();
    }


    public String getUserByUserId(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_users");
            WHERE("user_id = #{userId}");
        }}.toString();
    }


    public String test(int uId){
        return new SQL(){{
            SELECT("w.*, u.name, u.id");
            FROM("dp_wishlist as w");
            INNER_JOIN("dp_users as u ON w.u_id = u.id");
            WHERE("w.u_id = #{uId}");
        }}.toString();
    }

}
