package com.example.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class PromotionProvider {

    public String getPromotions(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_promotion");
        }}.toString();
    }
}
