package com.example.derphsar_api.repository;

import com.example.derphsar_api.repository.dto.ShopDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
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
    @Delete("delete FROM dp_shops where id = #{id}")
    void delete(int id);
}
