package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.repository.dto.UserDto;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    //find user by email
    public String findByEmail(String email){
        return new SQL(){{
            SELECT("u.*, ur.*, r.name AS \"role_name\"");
            FROM("dp_user_role ur");
            INNER_JOIN("dp_users u ON u.id = ur.user_id");
            INNER_JOIN("dp_role r ON ur.role_id = r.id");
            WHERE("u.email LIKE #{email}");
        }}.toString();
    }

    //insert new user
    public String insertUser(UserDto user){
        return new SQL(){{
            INSERT_INTO("dp_users");
            VALUES("user_id, name, gender, age, phone, email, password ,status, profile", "#{userId}, #{name}, #{gender}, #{age}, #{phone}, #{email}, #{password}, TRUE, #{profile}");
        }}.toString();
    }

    //insert user role by default ROLE_BUYER
    public String insertUserRole(UserDto user){
        return new SQL(){{
            INSERT_INTO("dp_user_role");
            VALUES("user_id, role_id", "(SELECT id FROM dp_users WHERE email LIKE #{email}), (SELECT id FROM dp_role WHERE name LIKE 'ROLE_BUYER')");
        }}.toString();
    }

    //select all user
    public String getAllUsers(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_users");
        }}.toString();
    }

    //select a user by id
    public String getOneUserById(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_users");
            WHERE("user_id = #{userId}");
        }}.toString();
    }


}
