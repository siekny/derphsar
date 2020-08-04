package com.kshrd.derphsar_api.repository;


import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.provider.UserProvider;
import com.kshrd.derphsar_api.rest.role.response.RoleResponse;
import com.kshrd.derphsar_api.rest.user.request.UserRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    @SelectProvider(type = UserProvider.class, method = "findByEmail")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "role.name", column = "role_name"),
            @Result(property = "role.id", column = "id"),
            @Result(property = "role.roleId", column = "role_id"),
    })
    UserDto findByEmail(String email);


    @InsertProvider(type = UserProvider.class, method = "insertUser")
    boolean insertUser(UserDto user);


    @InsertProvider(type = UserProvider.class, method = "insertUserRole")
    boolean insertUserRole(UserDto user);


    @SelectProvider(type = UserProvider.class, method = "getAllUsers")
    @Results({
            @Result(property = "userId", column = "user_id")
    })
    List<UserResponseModel> getAllUsers();


    @Select("SELECT r.id, r.name, r.role_id FROM dp_role r INNER JOIN dp_user_role ur ON r.id = ur.role_id" +
            " INNER JOIN dp_users u ON u.id = ur.user_id\n" +
            "WHERE u.id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "role_id", property = "roleId")
    }
    )
    List<RoleResponse> role(int id);



    @SelectProvider(type = UserProvider.class, method = "getOneUserById")
    @Results(
            @Result(column = "user_id", property = "userId")
    )
    UserResponseModel getOneUserById(String userId);


//    @SelectProvider(type = UserProvider.class, method = "deleteUserById")
//    Boolean deleteUserById(String userId);

    @Update("UPDATE dp_users SET status = 'FALSE' WHERE user_id = #{userId}")
    void deleteOneUserById(String userId);

}
