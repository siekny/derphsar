package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.rest.shop.response.ShopResponseModel;
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


    /**
     * Post a shop
     *
     * @param shopRequestModel - Shop request model
     * @return - Return response message
     */
    @PostMapping("/shops")
    @ApiOperation(value = "create a shop", response = ShopResponseModel.class)
    public ResponseEntity<BaseApiResponse<ShopResponseModel>> createShop(@RequestBody ShopRequestModel shopRequestModel){

        BaseApiResponse<ShopResponseModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        ShopDto shopDto = mapper.map(shopRequestModel, ShopDto.class);

        UUID uuid = UUID.randomUUID();
        shopDto.setShopId("DP"+uuid.toString().substring(0,10));
        shopDto.setStatus(true);

        try {
            ShopDto result = shopServiceImp.createShop(shopDto);
            ShopResponseModel responseModel = mapper.map(result, ShopResponseModel.class);

            response.setMessage(message.inserted("Shop"));
            response.setData(responseModel);
            response.setStatus(HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get shops
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("/shops")
    @ApiOperation(value = "show all shops", response = ShopResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ShopResponseModel>>> getShops(@RequestParam(value = "page" , required = false , defaultValue = "1") int page,
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
        BaseApiResponse<List<ShopResponseModel>> response = new BaseApiResponse<>();
        List<ShopResponseModel> shops = new ArrayList<>();

        try {
            List<ShopDto> shop = shopServiceImp.getShops(pagination);
            for (ShopDto shopDto : shop) {
                shops.add(mapper.map(shopDto, ShopResponseModel.class));
            }
            if (!shop.isEmpty()) {
                response.setMessage(message.selected("Shops"));
                response.setData(shops);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Shops"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
            response.setPagination(pagination);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Delete a shop
     *
     * @param shop_id - Id of a shop
     * @return - Return response message
     */
    @DeleteMapping("/shops/{shop_id}")
    @ApiOperation(value = "delete a shops", response = Void.class)
    public ResponseEntity<BaseApiResponse<Void>> deleteShop(@PathVariable("shop_id") String shop_id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        try {
            shopServiceImp.deleteShop(shop_id);
            response.setMessage(message.deleted("Shop"));
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a shop
     *
     * @param shop_id - Id of a shop
     * @param shopRequestModel - Shop request model
     * @return - Return response message
     */
    @PutMapping("/shops/{shop_id}")
    @ApiOperation(value = "update a shops", response = ShopResponseModel.class)
    public ResponseEntity<BaseApiResponse<ShopResponseModel>> updateShop(@PathVariable("shop_id") String shop_id,
                                                                        @RequestBody ShopRequestModel shopRequestModel){

        ModelMapper modelMapper = new ModelMapper();
        BaseApiResponse<ShopResponseModel> response=new BaseApiResponse <>();
        try {
            ShopDto dto = modelMapper.map(shopRequestModel,ShopDto.class);
            ShopResponseModel responseModel = modelMapper.map(shopServiceImp.updateShop(shop_id,dto),ShopResponseModel.class);

            response.setMessage(message.updated("Shop"));
            response.setStatus(HttpStatus.OK);
            response.setData(responseModel);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get a shop
     *
     * @param shopId - Id of a shop
     * @return - Return response message
     */
    @GetMapping("/shop/{shopId}")
    @ApiOperation(value = "search a shop by shop id", response = ShopResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ShopResponseModel>>> findById(@PathVariable("shopId") String shopId){

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopResponseModel>> response = new BaseApiResponse<>();
        List<ShopResponseModel> shops = new ArrayList<>();

        try {
            ShopDto shopDto = shopServiceImp.findById(shopId);
            shops.add(mapper.map(shopDto, ShopResponseModel.class));

            response.setMessage(message.selectedOne("Shop"));
            response.setData(shops);
            response.setStatus(HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Get a shop
     *
     * @param userId - Id of a user
     * @return - Return response message
     */
    @GetMapping("shops/{userId}")
    @ApiOperation(value = "show all shops by user id", response = ShopResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ShopResponseModel>>> getAllShopsByUserId(@PathVariable("userId") String userId) {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ShopResponseModel>> response = new BaseApiResponse<>();
        List<ShopResponseModel> shops = new ArrayList<>();

        try {
            UserDto userDto = shopServiceImp.getUserByUserId(userId);
            List<ShopDto> shop = shopServiceImp.getAllShopsByUserId(userDto.getId());
            for (ShopDto shopDto : shop) {
                shops.add(mapper.map(shopDto, ShopResponseModel.class));
            }

            if (!shop.isEmpty()) {
                response.setMessage(message.selected("Shops"));
                response.setData(shops);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Shops"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
