package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String findByEmail(String email){
        return new SQL(){{
            SELECT("u.*, ur.*, r.name AS \"role_name\"");
            FROM("dp_user_role ur");
            INNER_JOIN("dp_users u ON u.id = ur.user_id");
            INNER_JOIN("dp_role r ON ur.role_id = r.id");
            WHERE("u.email LIKE #{email}");
        }}.toString();
    }
}
