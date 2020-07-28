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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ShopRestController {

    private ShopServiceImp shopServiceImp;

    @Autowired
    public void setShopServiceImp(ShopServiceImp shopServiceImp) {
        this.shopServiceImp = shopServiceImp;
    }

    @PostMapping("/shop")
    public ResponseEntity<BaseApiResponse<ShopRequestModel>> createShop(@RequestBody ShopRequestModel shop){

        BaseApiResponse<ShopRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        ShopDto shopDto = mapper.map(shop, ShopDto.class);

        UUID uuid = UUID.randomUUID();
        shopDto.setShop_id(uuid.toString());

        ShopDto result = shopServiceImp.createShop(shopDto);
        ShopRequestModel requestModel = mapper.map(result, ShopRequestModel.class);

        response.setMessage("you have inserted a shop successfully!");
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

        List<ShopDto> shopDtoList = shopServiceImp.getShops();
        List<ShopRequestModel> shops = new ArrayList<>();
        for (ShopDto shopDto : shopDtoList) {
            shops.add(mapper.map(shopDto, ShopRequestModel.class));
        }
        response.setMessage("you have selected all shops successfully!");
        response.setData(shops);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/shops/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteShop(@PathVariable("id") int id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        shopServiceImp.deleteShop(id);
        response.setMessage("you have deleted a shop successfully!");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
