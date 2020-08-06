package com.example.derphsar_api.service.implement;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.WishListRepository;
import com.example.derphsar_api.repository.dto.WishListDto;
import com.example.derphsar_api.service.WishLishService;
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

    //    create wishlist
    @Override
    public WishListDto createWishList(WishListDto wishListDto) {
        boolean isInserted = wishListRepository.insert(wishListDto);
        if (isInserted)
            return wishListDto;
        else
            return null;
    }

    //    get wishlist
    @Override
    public List<WishListDto> getWishLists(Pagination pagination){
        return wishListRepository.select(pagination);
    }

    @Override
    public List<WishListDto> getWishLists() {
        return null;
    }


    //    delete wishlist
    @Override
    public void deleteWishList(String wishlist_id) {
        wishListRepository.delete(wishlist_id);
    }

    //count all id
    @Override
    public int countId() {
        return wishListRepository.countId();
    }
}
