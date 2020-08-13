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
    @ApiOperation(value = "show all promotions by a shop id", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<PromotionResponseModel>>> getPromotions(@RequestParam(value="shopId",required = false,defaultValue = "0") int shopId) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionResponseModel>> response = new BaseApiResponse<>();
        List<PromotionResponseModel> promotions = new ArrayList<>();

        try {
            List<PromotionDto> promotion = promotionServiceImp.getPromotions(shopId);
            for (PromotionDto promotionDto : promotion) {
                promotions.add(mapper.map(promotionDto, PromotionResponseModel.class));
            }

            if (!promotion.isEmpty()) {
                response.setMessage(message.selected("Promotions"));
                response.setData(promotions);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Promotions"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Delete a promotion
     *
     * @param promoId - Id of a promotion
     * @return - Return response message
     */
    @DeleteMapping("/promotions/{promoId}")
    @ApiOperation(value = "delete a promotion by a promotion id", response = Void.class)
    public ResponseEntity<BaseApiResponse<Void>> deletePromotion(@PathVariable("promoId") String promoId) {

        BaseApiResponse<Void> response = new BaseApiResponse<>();

        try {
            promotionServiceImp.deletePromotion(promoId);
            response.setMessage(message.deleted("Promotion"));
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a promotion
     *
     * @param promoId - Id of a promotion
     * @param promotionRequestModel - Promotion request model
     * @return - Return response message
     */
    @PutMapping("/promotions/{promoId}")
    @ApiOperation(value = "update a promotion by a promotion id", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<PromotionResponseModel>> updatePromotion(@PathVariable("promoId") String promoId,
                                                                                  @RequestBody PromotionRequestModel promotionRequestModel) {

        ModelMapper modelMapper = new ModelMapper();
        BaseApiResponse<PromotionResponseModel> response = new BaseApiResponse<>();

        try {
            PromotionDto promotionDto = modelMapper.map(promotionRequestModel, PromotionDto.class);
            PromotionResponseModel responseModel = modelMapper.map(promotionServiceImp.updatePromotion(promoId, promotionDto), PromotionResponseModel.class);

            response.setMessage(message.updated("Promotion"));
            response.setStatus(HttpStatus.OK);
            response.setData(responseModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Post a promotion
     *
     * @param promotionRequestModel - Promotion request model
     * @return - Return response message
     */
    @PostMapping("/promotions")
    @ApiOperation(value = "create a promotion", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<PromotionResponseModel>> createPromotion(@RequestBody PromotionRequestModel promotionRequestModel) {

        BaseApiResponse<PromotionResponseModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        UUID uuid = UUID.randomUUID();

        try {
            PromotionDto promotionDto = mapper.map(promotionRequestModel, PromotionDto.class);
            promotionDto.setPromoId("DP"+uuid.toString().substring(0,10));
            promotionDto.setStatus(true);

            PromotionDto result = promotionServiceImp.createPromotion(promotionDto);
            PromotionResponseModel responseModel = mapper.map(result, PromotionResponseModel.class);

            response.setMessage(message.inserted("Promotion"));
            response.setData(responseModel);
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get a promotion
     *
     * @param promoId - Id of a promotion
     * @return - Return response message
     */
    @GetMapping("/promotions/{promoId}")
    @ApiOperation(value = "get a promotion by a promotion id", response = PromotionResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<PromotionResponseModel>>> findById(@PathVariable("promoId") String promoId){

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<PromotionResponseModel>> response =
                new BaseApiResponse<>();
        List<PromotionResponseModel> promotionRequestModels = new ArrayList<>();

        try {
            PromotionDto promotionDto = promotionServiceImp.findById(promoId);
            promotionRequestModels.add(mapper.map(promotionDto, PromotionResponseModel.class));

            response.setMessage(message.selectedOne("Promotion"));
            response.setData(promotionRequestModels);
            response.setStatus(HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}