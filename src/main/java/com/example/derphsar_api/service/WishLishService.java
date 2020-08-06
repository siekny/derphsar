package com.example.derphsar_api.service;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.WishListDto;

import java.util.List;

public interface WishLishService {
    //create wishlist
    WishListDto createWishList(WishListDto wishListDto);

    //get all wishlist
    List<WishListDto> getWishLists(Pagination pagination);

    //get wishlist
    List<WishListDto> getWishLists();

    //delete wishlist
    void deleteWishList(String wishlist_id);

    //count wishlist
    int countId();
}
