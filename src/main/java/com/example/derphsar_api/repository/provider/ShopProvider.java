package com.example.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class ShopProvider {

    public String getShops() {
        return new SQL(){{
            SELECT("*");
            FROM("dp_shops");
        }}.toString();
    }
}
