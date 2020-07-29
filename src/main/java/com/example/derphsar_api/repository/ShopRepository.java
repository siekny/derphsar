package com.example.derphsar_api.repository;

import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.repository.dto.ShopDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShopRepository {

//    Insert shop
    @Insert("INSERT INTO dp_shops(shop_id, name, address, description, profile, cover, is_open, status, working_time, u_id, cat_id) "+
            "VALUES (#{shop_id}, #{name}, #{address}, #{description}, #{profile}, #{cover}, #{is_open}, #{status}, #{working_time}, #{u_id}, #{cat_id})")

    boolean insert(ShopDto shop);

//    Get shops
    @Select("SELECT * FROM dp_shops")
    List<ShopDto> select();

//    Delete shop
    @Delete("delete FROM dp_shops where shop_id = #{shop_id}")
    void delete(String shop_id);

//    Update shop
    @Update("UPDATE dp_shops set name = #{shop.name}, address = #{shop.address}, description= #{shop.description} , profile= #{shop.profile} , cover= #{shop.cover} , status = #{shop.status}, is_open = #{shop.is_open}, working_time= #{shop.working_time} WHERE shop_id = #{shop_id}")
    boolean update(String shop_id, ShopDto shop);
}
