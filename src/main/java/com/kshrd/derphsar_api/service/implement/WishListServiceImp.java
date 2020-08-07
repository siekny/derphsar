package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.WishListRepository;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.wishlist.respone.WishListResponse;
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

    @Override
    public List<WishListDto> getAllWishListByUserId(int userId) {
        return wishListRepository.getAllShopsByUserId(userId);
    }

    @Override
    public List<WishListResponse> test(int userId) {
        System.out.println(wishListRepository.test(userId));
        return wishListRepository.test(userId);
    }


    @Override
    public UserDto getUserByUserId(String userId) {
        return wishListRepository.getUserByUserId(userId);
    }

}
