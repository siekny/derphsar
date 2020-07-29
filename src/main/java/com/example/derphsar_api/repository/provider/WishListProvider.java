package com.example.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class WishListProvider {

    public String getWishList() {
        return new SQL(){{
            SELECT("*");
            FROM("dp_wishlist");
        }}.toString();
    }

}
