package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.WishListDto;

import java.util.List;

public interface WishLishService {
    //    create wishlist
    WishListDto createWishList(WishListDto wishListDto);

    //    get all wishlist
    List<WishListDto> getWishLists();

    //    delete wishlist
    void deleteWishList(String wishlist_id);

}