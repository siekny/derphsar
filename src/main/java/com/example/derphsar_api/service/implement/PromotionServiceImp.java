package com.example.derphsar_api.service.implement;

import com.example.derphsar_api.repository.PromotionRepository;
import com.example.derphsar_api.repository.dto.PromotionDto;
import com.example.derphsar_api.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PromotionServiceImp implements PromotionService {
    private PromotionRepository promotionRepository;

    @Autowired
    public void setPromotionRepository(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<PromotionDto> getPromotions(int shopId) {
        if (shopId == 0)
            return promotionRepository.getPromotions();
        else
            return promotionRepository.findPromotionByShopId(shopId);
    }

    @Override
    public void deletePromotion(String id) {
        promotionRepository.deletePromotion(id);
    }

    @Override
    public PromotionDto updatePromotion(String id, PromotionDto promotionDto) {
        boolean isUpdated = promotionRepository.updatePromotion(id,promotionDto);
        if(isUpdated){
            return promotionDto;
        }
        return null;
    }

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        System.out.println("promotion"+promotionDto);
        boolean isInserted = promotionRepository.createPromotion(promotionDto);
        if(isInserted)
            return promotionDto;
        else
            return null;
    }

    //find by id
    @Override
    public PromotionDto findById(String promoId) {
        return  promotionRepository.findById(promoId);
    }
}
