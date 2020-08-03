package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.ShopRepository;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImp implements ShopService {

    private ShopRepository shopRepository;

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    //create a shop
    @Override
    public ShopDto createShop(ShopDto shop) {
        boolean isInserted = shopRepository.insert(shop);
        if (isInserted)
            return shop;
        else
            return null;
    }

    //get all shops
    @Override
    public List<ShopDto> getShops(){
        return shopRepository.select();
    }

    //delete a shop
    @Override
    public void deleteShop(String shop_id) {
        shopRepository.delete(shop_id);
    }

    //update a shop
    @Override
    public ShopDto updateShop(String shop_id, ShopDto shop) {
        boolean isUpdated = shopRepository.update(shop_id,shop);
        if(isUpdated){
            return shop;
        }
        return null;
    }

    //find a shop by id
    @Override
    public ShopDto findById(String shopId) {
        return  shopRepository.findById(shopId);
    }
}