package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.service.implement.ShopServiceImp;
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
public class ShopRestController {

    private ShopServiceImp shopServiceImp;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

//    create a shop
    @PostMapping("/shops")
    @ApiOperation(value = "create a shop", response = ShopRequestModel.class)
    public ResponseEntity<BaseApiResponse<ShopRequestModel>> createShop(@RequestBody ShopRequestModel shop){

        BaseApiResponse<ShopRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        ShopDto shopDto = mapper.map(shop, ShopDto.class);

        UUID uuid = UUID.randomUUID();
        shopDto.setShopId("DP"+uuid.toString().substring(0,10));

        ShopDto result = shopServiceImp.createShop(shopDto);
        ShopRequestModel requestModel = mapper.map(result, ShopRequestModel.class);

        response.setMessage(message.inserted("Shop"));
        response.setData(requestModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //get all shops
//    @GetMapping("/shops")
//    @ApiOperation(value = "show all shops", response = ShopRequestModel.class)
//    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> getShops() {
//
//        ModelMapper mapper = new ModelMapper();
//        BaseApiResponse<List<ShopRequestModel>> response =
//                new BaseApiResponse<>();
//
//        List<ShopDto> shopDtoList = shopServiceImp.getShops();
//        List<ShopRequestModel> shops = new ArrayList<>();
//        for (ShopDto shopDto : shopDtoList) {
//            shops.add(mapper.map(shopDto, ShopRequestModel.class));
//        }
//        response.setMessage(message.selected("Shops"));
//        response.setData(shops);
//        response.setStatus(HttpStatus.OK);
//        response.setTime(new Timestamp(System.currentTimeMillis()));
//        return ResponseEntity.ok(response);
//    }


    //get all shops
    @GetMapping("/shops")
    @ApiOperation(value = "show all shops", response = ShopRequestModel.class)
    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> getShops(@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                            @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                            @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                            @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();


        pagination.setTotalCount(shopServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopRequestModel>> response =
                new BaseApiResponse<>();

        List<ShopDto> shopDtoList = shopServiceImp.getShops(pagination);
        List<ShopRequestModel> shops = new ArrayList<>();
        for (ShopDto shopDto : shopDtoList) {
            shops.add(mapper.map(shopDto, ShopRequestModel.class));
        }

        response.setPagination(pagination);
        response.setMessage("you have selected all shops successfully!");
        response.setData(shops);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //delete a shop
    @DeleteMapping("/shops/{shop_id}")
    @ApiOperation(value = "delete a shops", response = Void.class)
    public ResponseEntity<BaseApiResponse<Void>> deleteShop(@PathVariable("shop_id") String shop_id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        shopServiceImp.deleteShop(shop_id);
        response.setMessage(message.deleted("Shop"));
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //update a shop
    @PutMapping("/shops/{shop_id}")
    @ApiOperation(value = "update a shops", response = ShopRequestModel.class)
    public ResponseEntity<BaseApiResponse<ShopRequestModel>> updateShop(
            @PathVariable("shop_id") String shop_id,
            @RequestBody ShopRequestModel shopRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        ShopDto dto = modelMapper.map(shopRequestModel,ShopDto.class);
        ShopRequestModel responseModel = modelMapper.map(shopServiceImp.updateShop(shop_id,dto),ShopRequestModel.class);

        BaseApiResponse<ShopRequestModel> response=new BaseApiResponse <>();
        response.setMessage(message.updated("Shop"));
        response.setStatus(HttpStatus.OK);
        response.setData(responseModel);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //find by id
    @GetMapping("/shops/{id}")
    @ApiOperation(value = "show a shop by shop id", response = ShopRequestModel.class)
    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> findById(@PathVariable("id") String id){
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopRequestModel>> response =
                new BaseApiResponse<>();

        ShopDto shopDto = shopServiceImp.findById(id);
        List<ShopRequestModel> shopRequestModels = new ArrayList<>();

        shopRequestModels.add(mapper.map(shopDto, ShopRequestModel.class));
        response.setMessage(message.selectedOne("Shop"));
        response.setData(shopRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    @GetMapping("shops/{userId}")
    @ApiOperation(value = "get all shops by user id", response = ShopRequestModel.class)
    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> getAllShopsByUserId(@PathVariable("userId") String userId) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopRequestModel>> response =
                new BaseApiResponse<>();

        UserDto userDto = shopServiceImp.getUserByUserId(userId);
        List<ShopDto> shopDtoList = shopServiceImp.getAllShopsByUserId(userDto.getId());
        List<ShopRequestModel> shops = new ArrayList<>();
        for (ShopDto shopDto : shopDtoList) {
            shops.add(mapper.map(shopDto, ShopRequestModel.class));
        }
        response.setMessage(message.selected("Shops"));
        response.setData(shops);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
