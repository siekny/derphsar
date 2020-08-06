package com.example.derphsar_api.service.implement;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.ShopRepository;
import com.example.derphsar_api.repository.dto.ShopDto;
import com.example.derphsar_api.service.ShopService;
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

    //create shop
    @Override
    public ShopDto createShop(ShopDto shop) {
        boolean isInserted = shopRepository.insert(shop);
        if (isInserted)
            return shop;
        else
            return null;
    }

    //get shops
    @Override
    public List<ShopDto> getShops(Pagination pagination){
        return shopRepository.select(pagination);
    }

    //delete shop
    @Override
    public void deleteShop(String shopId) {
        shopRepository.delete(shopId);
    }

    //update shop
    @Override
    public ShopDto updateShop(String shopId, ShopDto shop) {
        boolean isUpdated = shopRepository.update(shopId,shop);
        if(isUpdated){
            return shop;
        }
        return null;
    }

    //find by id
    @Override
    public ShopDto findById(String shopId) {
        return  shopRepository.findById(shopId);
    }

    //count all id
    @Override
    public int countId() {
        return shopRepository.countId();
    }
}
