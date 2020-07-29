package com.example.derphsar_api.repository;

import com.example.derphsar_api.repository.dto.WishListDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WishListRepository {

    //    Insert wishlist
    @Insert("INSERT INTO dp_wishlist(wishlist_id, fav_date, u_id, pro_id) "+
            "VALUES (#{wishlist_id}, #{fav_date}, #{u_id}, #{pro_id})")

    boolean insert(WishListDto wishListDto);

    //    Get wishlist
    @Select("SELECT * FROM dp_wishlist")
    List<WishListDto> select();

    //    Delete wishlist
    @Delete("delete FROM dp_wishlist where wishlist_id = #{wishlist_id}")
    void delete(String wishlist_id);

}
