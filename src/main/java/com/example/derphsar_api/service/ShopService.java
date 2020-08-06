package com.example.derphsar_api.service;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.ShopDto;
import java.util.List;

public interface ShopService {

    //create shop
    ShopDto createShop(ShopDto shop);

    //get all shop
    List<ShopDto> getShops(Pagination pagination);

    //delete shop
    void deleteShop(String shopId);

    //update shop
    ShopDto updateShop(String shopId, ShopDto shop);

    //find by id
    ShopDto findById(String shopId);

    //count shop
    int countId();
}
