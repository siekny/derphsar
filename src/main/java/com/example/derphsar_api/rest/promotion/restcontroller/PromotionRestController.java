package com.example.derphsar_api.rest.promotion.restcontroller;


import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.PromotionDto;
import com.example.derphsar_api.rest.BaseApiResponse;
import com.example.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.example.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.example.derphsar_api.service.implement.PromotionServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PromotionRestController {

    private PromotionServiceImp promotionServiceImp;

    @Autowired
    public void setPromotionServiceImp(PromotionServiceImp promotionServiceImp) {
        this.promotionServiceImp = promotionServiceImp;
    }

    //get all promotions
    @GetMapping("/promotions")
    public ResponseEntity<BaseApiResponse<List<PromotionResponseModel>>> select(@RequestParam(value="shopId",required = false,defaultValue = "0") int shopId,
                                                                                @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit) {


        Pagination pagination = new Pagination(page, limit);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();


        pagination.setTotalCount(promotionServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionResponseModel>> response =
                new BaseApiResponse<>();

        List<PromotionDto> promotionDtoList = promotionServiceImp.getPromotions(pagination,shopId);
        List<PromotionResponseModel> promotions = new ArrayList<>();

        for (PromotionDto promotionDto : promotionDtoList) {
            promotions.add(mapper.map(promotionDto, PromotionResponseModel.class));
        }

        response.setPagination(pagination);
        response.setMessage("You have found all articles successfully");
        response.setData(promotions);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }





    //Delete a promotion
    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deletePromotion(@PathVariable("id") String id) {
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        promotionServiceImp.deletePromotion(id);
        response.setMessage("you have deleted promotion successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //Update a promotions
    @PutMapping("/promotions/{id}")
    public ResponseEntity<BaseApiResponse<PromotionRequestModel>> updatePromotions(
            @PathVariable("id") String id,
            @RequestBody PromotionRequestModel promotionRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        PromotionDto dto = modelMapper.map(promotionRequestModel, PromotionDto.class);
        PromotionRequestModel responeModel = modelMapper.map(promotionServiceImp.updatePromotion(id, dto), PromotionRequestModel.class);

        BaseApiResponse<PromotionRequestModel> respone = new BaseApiResponse<>();
        respone.setMessage("YOU HAVE UPDATED SUCCESSFULLY!");
        respone.setStatus(HttpStatus.OK);
        respone.setData(responeModel);
        respone.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(respone);


    }


    //post promotion
    @PostMapping("/promotions")
    public ResponseEntity<BaseApiResponse<PromotionRequestModel>> createPromotion(
            @RequestBody PromotionRequestModel promotionRequestModel) {


        BaseApiResponse<PromotionRequestModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        PromotionDto promotionDto = mapper.map(promotionRequestModel, PromotionDto.class);

        UUID uuid = UUID.randomUUID();
        promotionDto.setPromoId("DP"+uuid.toString().substring(0,10));


        PromotionDto result = promotionServiceImp.createPromotion(promotionDto);
        PromotionRequestModel result2 = mapper.map(result, PromotionRequestModel.class);
        response.setMessage("You have added product successfully");
        response.setData(result2);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);

    }

    //find by id
    @GetMapping("/promotions/{id}")
    public ResponseEntity<BaseApiResponse<List<PromotionRequestModel>>> findById(@PathVariable("id") String id){
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionRequestModel>> response =
                new BaseApiResponse<>();

        PromotionDto promotionDto = promotionServiceImp.findById(id);
        List<PromotionRequestModel> promotionRequestModels = new ArrayList<>();

        promotionRequestModels.add(mapper.map(promotionDto, PromotionRequestModel.class));
        response.setMessage("You have found promotion by id successfully");
        response.setData(promotionRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}