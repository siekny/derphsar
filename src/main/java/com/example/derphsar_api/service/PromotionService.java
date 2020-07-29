package com.example.derphsar_api.service;

import com.example.derphsar_api.repository.dto.PromotionDto;

import java.util.List;

public interface PromotionService {

    //get all promotion
    List<PromotionDto> getPromotions();


    //delete a promotion
    void deletePromotion(String id);

    //update a promotion
    PromotionDto updatePromotion(String id, PromotionDto promotionDto);
}
