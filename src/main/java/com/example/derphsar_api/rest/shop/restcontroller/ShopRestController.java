package com.example.derphsar_api.rest.shop.restcontroller;

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

@RestController
@RequestMapping("/api/v1")
public class ShopRestController {

    private ShopServiceImp shopServiceImp;

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

    @PostMapping("/shops")
    public ResponseEntity<BaseApiResponse<ShopRequestModel>> createShop(@RequestBody ShopRequestModel shop){

        BaseApiResponse<ShopRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        ShopDto shopDto = mapper.map(shop, ShopDto.class);

        ShopDto result = shopServiceImp.insert(shopDto);
        ShopRequestModel requestModel = mapper.map(result, ShopRequestModel.class);

        response.setMessage("You inserted successfully!");
        response.setData(requestModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/shops")
    public ResponseEntity<BaseApiResponse<List<ShopRequestModel>>> getShops() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopRequestModel>> response =
                new BaseApiResponse<>();

        List<ShopDto> shopDtoList = shopServiceImp.select();
        List<ShopRequestModel> shops = new ArrayList<>();
        for (ShopDto shopDto : shopDtoList) {
            shops.add(mapper.map(shopDto, ShopRequestModel.class));
        }
        response.setMessage("You selected successfully!");
        response.setData(shops);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    //Delete a shop
    @DeleteMapping("/shops/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteShops(@PathVariable("id") String id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        shopServiceImp.deleteShop(id);
        response.setMessage("you have deleted promotion successfully");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
