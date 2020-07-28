package com.example.derphsar_api.service;

import com.example.derphsar_api.repository.dto.ShopDto;
import java.util.List;

public interface ShopService {

//    create shop
    ShopDto createShop(ShopDto shop);

//    get all shop
    List<ShopDto> getShops();

//    delete shop
    void deleteShop(int id);
}
