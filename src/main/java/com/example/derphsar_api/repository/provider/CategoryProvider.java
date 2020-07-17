package com.example.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {
    public String select(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_category");
        }}.toString();
    }
}
