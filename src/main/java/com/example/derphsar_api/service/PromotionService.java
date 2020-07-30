package com.example.derphsar_api.service;

import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.repository.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    //get all promotion
    List<PromotionDto> getPromotions(int shopId);


    //delete a promotion
    void deletePromotion(String id);

    //update a promotion
    PromotionDto updatePromotion(String id, PromotionDto promotionDto);

    //create a promotion
    PromotionDto createPromotion(PromotionDto promotionDto);
}
