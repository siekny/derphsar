package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.provider.ShopProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShopRepository {

//    Insert shop
    @Insert("INSERT INTO dp_shops(shop_id, name, address, description, profile, cover, is_open, status, working_time, u_id, cat_id) "+
            "VALUES (#{shopId}, #{name}, #{address}, #{description}, #{profile}, #{cover}, #{isOpen}, #{status}, #{workingTime}, #{u_id}, #{cat_id})")

    boolean insert(ShopDto shop);


//    Get shops
    @SelectProvider(value = ShopProvider.class, method = "getShops")
    @Results({
            @Result(column = "shop_id" ,property = "shopId"),
            @Result(column = "is_open" ,property = "isOpen"),
            @Result(column = "working_time" ,property = "workingTime")
    })
    List<ShopDto> select();


//    Delete shop
    @Delete("delete FROM dp_shops where shop_id = #{shop_id}")
    void delete(String shop_id);

//    Update shop
    @Update("UPDATE dp_shops set name = #{shop.name}, address = #{shop.address}, description= #{shop.description} , profile= #{shop.profile} , cover= #{shop.cover} , status = #{shop.status}, is_open = #{shop.isOpen}, working_time= #{shop.workingTime} WHERE shop_id = #{shop_id}")
    boolean update(String shop_id, ShopDto shop);

//    find by id
    @Select("SELECT * FROM dp_shops WHERE shop_id = #{shopId}")
    ShopDto findById(String shopId);
}
