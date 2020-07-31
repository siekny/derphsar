package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.WishListRepository;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.service.WishLishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImp implements WishLishService{

    private WishListRepository wishListRepository;

    @Autowired
    public void setWishListRepository(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    //create wishlist
    @Override
    public WishListDto createWishList(WishListDto wishListDto) {
        boolean isInserted = wishListRepository.insert(wishListDto);
        if (isInserted)
            return wishListDto;
        else
            return null;
    }

    //get all wishlists
    @Override
    public List<WishListDto> getWishLists(){
        return wishListRepository.select();
    }

    //delete  a wishlist
    @Override
    public void deleteWishList(String wishlist_id) {
        wishListRepository.delete(wishlist_id);
    }

}
