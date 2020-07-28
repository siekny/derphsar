package com.example.derphsar_api.repository;

import com.example.derphsar_api.repository.dto.ShopDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository {

    @Insert("INSERT INTO dp_shops (id, shop_id, shop_name, shop_adress, shop_description, shop_profile, shop_cover, shop_status, status, working_time, u_id, cat_id) "+
            "VALUES (#{id}, #{shop_id}, #{shop_name}, #{shop_adress}, #{shop_description}, #{shop_profile}, #{shop_cover}, #{shop_status}, #{status}, #{working_time}, #{u_id}, #{cat_id})")

    boolean insert(ShopDto shop);


    @Select("SELECT * FROM dp_shops")
    List<ShopDto> select();


    //delete shop
    @Delete("delete FROM dp_shops WHERE shop_id =#{id}")
    void deleteShop(String id);
}
