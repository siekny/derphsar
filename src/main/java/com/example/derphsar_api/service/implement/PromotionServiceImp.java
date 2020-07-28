package com.example.derphsar_api.service.implement;

import com.example.derphsar_api.repository.PromotionRepository;
import com.example.derphsar_api.repository.dto.ProductDto;
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
    public List<PromotionDto> getPromotions() {
        return promotionRepository.getPromotions();
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
}
