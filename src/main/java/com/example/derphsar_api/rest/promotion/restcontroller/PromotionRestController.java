package com.example.derphsar_api.rest.promotion.restcontroller;


import com.example.derphsar_api.repository.dto.PromotionDto;
import com.example.derphsar_api.rest.BaseApiResponse;
import com.example.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.example.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.example.derphsar_api.service.implement.PromotionServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PromotionRestController {

    private PromotionServiceImp promotionServiceImp;

    @Autowired
    public void setPromotionServiceImp(PromotionServiceImp promotionServiceImp) {
        this.promotionServiceImp = promotionServiceImp;
    }


    //get all promotions
     @GetMapping("/promotions")
    public ResponseEntity<BaseApiResponse<List<PromotionResponseModel>>> select() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionResponseModel>> response =
                new BaseApiResponse<>();

        List<PromotionDto> promotionDtoList = promotionServiceImp.getPromotions();
        List<PromotionResponseModel> promotions = new ArrayList<>();

        for (PromotionDto promotionDto : promotionDtoList) {
            promotions.add(mapper.map(promotionDto, PromotionResponseModel.class));
        }

        response.setMessage("You have found all articles successfully");
        response.setData(promotions);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

}
