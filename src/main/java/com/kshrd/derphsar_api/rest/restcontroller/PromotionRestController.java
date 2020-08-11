package com.kshrd.derphsar_api.rest.restcontroller;


import com.kshrd.derphsar_api.repository.dto.PromotionDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.service.implement.PromotionServiceImp;
import io.swagger.annotations.ApiOperation;
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
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setPromotionServiceImp(PromotionServiceImp promotionServiceImp) {
        this.promotionServiceImp = promotionServiceImp;
    }





    /**
     * Get promotions
     *
     * @param shopId - Id of a shop
     * @return - Return response message
     */
    @GetMapping("/promotions")
    @ApiOperation(value = "show all promotion", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<PromotionResponseModel>>> getPromotions(@RequestParam(value="shopId",required = false,defaultValue = "0") int shopId) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionResponseModel>> response =
                new BaseApiResponse<>();

        List<PromotionDto> promotionDtoList = promotionServiceImp.getPromotions(shopId);
        List<PromotionResponseModel> promotions = new ArrayList<>();

        for (PromotionDto promotionDto : promotionDtoList) {
            promotions.add(mapper.map(promotionDto, PromotionResponseModel.class));
        }

        response.setMessage(message.selected("Promotions"));
        response.setData(promotions);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    /**
     * Delete a promotion
     *
     * @param id - Id of a promotion
     * @return - Return response message
     */
    @DeleteMapping("/promotions/{id}")
    @ApiOperation(value = "delete a promotion", response = Void.class)
    public ResponseEntity<BaseApiResponse<Void>> deletePromotion(@PathVariable("id") String id) {
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        promotionServiceImp.deletePromotion(id);
        response.setMessage(message.deleted("Promotion"));
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a promotion
     *
     * @param id - Id of a promotion
     * @param promotionRequestModel - Promotion request model
     * @return - Return response message
     */
    @PutMapping("/promotions/{id}")
    @ApiOperation(value = "update a promotion", response = PromotionRequestModel.class)
    public ResponseEntity<BaseApiResponse<PromotionRequestModel>> updatePromotion(
            @PathVariable("id") String id,
            @RequestBody PromotionRequestModel promotionRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        PromotionDto dto = modelMapper.map(promotionRequestModel, PromotionDto.class);
        PromotionRequestModel responeModel = modelMapper.map(promotionServiceImp.updatePromotion(id, dto), PromotionRequestModel.class);

        BaseApiResponse<PromotionRequestModel> respone = new BaseApiResponse<>();
        respone.setMessage(message.updated("Promotion"));
        respone.setStatus(HttpStatus.OK);
        respone.setData(responeModel);
        respone.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(respone);
    }


    /**
     * Post a promotion
     *
     * @param promotionRequestModel - Promotion request model
     * @return - Return response message
     */
    @PostMapping("/promotion")
    @ApiOperation(value = "create a promotion", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<PromotionResponseModel>> createPromotion(
            @RequestBody PromotionRequestModel promotionRequestModel) {

        BaseApiResponse<PromotionResponseModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        PromotionDto promotionDto = mapper.map(promotionRequestModel, PromotionDto.class);

        UUID uuid = UUID.randomUUID();
        promotionDto.setPromoId("DP"+uuid.toString().substring(0,10));
        promotionDto.setStatus(true);

        PromotionDto result = promotionServiceImp.createPromotion(promotionDto);
        PromotionResponseModel responseModel = mapper.map(result, PromotionResponseModel.class);
        response.setMessage(message.inserted("Promotion"));
        response.setData(responseModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);

    }


    /**
     * Get a promotion
     *
     * @param id - Id of a promotion
     * @return - Return response message
     */
    @GetMapping("/promotions/{id}")
    @ApiOperation(value = "show all promotions", response = PromotionRequestModel.class)
    public ResponseEntity<BaseApiResponse<List<PromotionRequestModel>>> findById(@PathVariable("id") String id){
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionRequestModel>> response =
                new BaseApiResponse<>();

        PromotionDto promotionDto = promotionServiceImp.findById(id);
        List<PromotionRequestModel> promotionRequestModels = new ArrayList<>();

        promotionRequestModels.add(mapper.map(promotionDto, PromotionRequestModel.class));
        response.setMessage(message.selectedOne("Promotion"));
        response.setData(promotionRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}