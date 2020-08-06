package com.example.derphsar_api.service;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    List<PromotionDto> getPromotions(Pagination pagination, int shopId);

    //get all promotion
    List<PromotionDto> getPromotions(int shopId);


    List<PromotionDto> getPromotions(Pagination pagination);

    //delete a promotion
    void deletePromotion(String id);

    //update a promotion
    PromotionDto updatePromotion(String id, PromotionDto promotionDto);

    //create a promotion
    PromotionDto createPromotion(PromotionDto promotionDto);

    //find by id
    PromotionDto findById(String promoId);

    //count shop
    int countId();
}
