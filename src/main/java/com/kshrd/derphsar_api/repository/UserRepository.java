package com.kshrd.derphsar_api.repository;


import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.provider.UserProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

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
}
