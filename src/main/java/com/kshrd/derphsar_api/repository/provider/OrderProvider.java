package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class OrderProvider {

    public String createOrder(){
        return new SQL(){{

        }}.toString();
    }
}
