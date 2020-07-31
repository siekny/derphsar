package com.example.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class OrderProvider {
    public String getOrders() {
        return new SQL(){{
            SELECT("*");
            FROM("dp_order");
        }}.toString();
    }
}
