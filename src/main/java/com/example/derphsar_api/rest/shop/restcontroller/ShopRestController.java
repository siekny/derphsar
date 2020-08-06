package com.example.derphsar_api.rest.shop.restcontroller;

import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.ShopDto;
import com.example.derphsar_api.rest.BaseApiResponse;
import com.example.derphsar_api.rest.shop.request.ShopRequestModel;
import com.example.derphsar_api.service.implement.ShopServiceImp;
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

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

    //create a shop
    @PostMapping("/shops")
    public ResponseEntity<BaseApiResponse<ShopRequestModel>> createShop(@RequestBody ShopRequestModel shop){

        BaseApiResponse<ShopRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        ShopDto shopDto = mapper.map(shop, ShopDto.class);


        UUID uuid = UUID.randomUUID();
        shopDto.setShopId("DP"+uuid.toString().substring(0,10));

        ShopDto result = shopServiceImp.createShop(shopDto);
        ShopRequestModel requestModel = mapper.map(result, ShopRequestModel.class);

        response.setMessage("you have inserted a shop successfully!");
        response.setData(requestModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //get all shops
    @GetMapping("/shops")
    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> getShops(@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                            @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit) {

        Pagination pagination = new Pagination(page, limit);
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
    @DeleteMapping("/shops/{shopId}")
    public ResponseEntity<BaseApiResponse<Void>> deleteShop(@PathVariable("shopId") String shopId){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        shopServiceImp.deleteShop(shopId);
        response.setMessage("you have deleted a shop successfully!");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //update a shop
    @PutMapping("/shops/{shopId}")
    public ResponseEntity<BaseApiResponse<ShopRequestModel>> updateShop(
            @PathVariable("shopId") String shopId,
            @RequestBody ShopRequestModel shopRequestModel){
        ModelMapper modelMapper = new ModelMapper();
        ShopDto dto = modelMapper.map(shopRequestModel,ShopDto.class);
        ShopRequestModel responseModel = modelMapper.map(shopServiceImp.updateShop(shopId,dto),ShopRequestModel.class);

        BaseApiResponse<ShopRequestModel> response=new BaseApiResponse <>();
        response.setMessage("you have updated a shop successfully!");
        response.setStatus(HttpStatus.OK);
        response.setData(responseModel);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //find by id
    @GetMapping("/shops/{id}")
    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> findById(@PathVariable("id") String id){
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopRequestModel>> response =
                new BaseApiResponse<>();

        ShopDto shopDto = shopServiceImp.findById(id);
        List<ShopRequestModel> shopRequestModels = new ArrayList<>();

        shopRequestModels.add(mapper.map(shopDto, ShopRequestModel.class));
        response.setMessage("You have found shop by id successfully");
        response.setData(shopRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
