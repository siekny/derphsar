package com.example.derphsar_api.repository;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.ShopDto;
import com.example.derphsar_api.repository.dto.WishListDto;
import com.example.derphsar_api.repository.provider.ShopProvider;
import com.example.derphsar_api.repository.provider.WishListProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WishListRepository {

    //Insert wishlist
    @Insert("INSERT INTO dp_wishlists(wishlist_id, fav_date, u_id, pro_id) "+
            "VALUES (#{wishlistId}, #{favDate}, #{u_id}, #{pro_id})")
    boolean insert(WishListDto wishListDto);

    //Get wishlist
    @Select("SELECT * FROM dp_wishlists LIMIT #{pagination.limit}  OFFSET #{pagination.offset}")
    @Results({
            @Result(column = "wishlist_id" ,property = "wishlistId"),
            @Result(column = "fav_date" ,property = "favDate")
    })
    List<WishListDto> select(@Param("pagination") Pagination pagination);

    //Delete wishlist
    @Delete("DELETE FROM dp_wishlists where wishlist_id = #{wishlistId}")
    void delete(String wishlistId);

    //count all wishlists
    @Select("SELECT COUNT(id) FROM dp_wishlists")
    int countId();
}
