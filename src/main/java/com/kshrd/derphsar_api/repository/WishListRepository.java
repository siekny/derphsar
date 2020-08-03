package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.repository.provider.WishListProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WishListRepository {

    //Insert wishlist
    @Insert("INSERT INTO dp_wishlist(wishlist_id, fav_date, u_id, pro_id) "+
            "VALUES (#{wishlistId}, #{favDate}, #{u_id}, #{pro_id})")
    boolean insert(WishListDto wishListDto);


    //Get all wishlist
    @SelectProvider(value = WishListProvider.class, method = "getWishList")
    @Results({
            @Result(column = "wishlist_id" ,property = "wishlistId"),
            @Result(column = "fav_date" ,property = "favDate")
    })
    List<WishListDto> select();


    //Delete a wishlist
    @Delete("delete FROM dp_wishlist where wishlist_id = #{wishlist_id}")
    void delete(String wishlist_id);

}