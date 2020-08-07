package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.shop.request.ShopRequestModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.wishlist.request.WishListRequestModel;
import com.kshrd.derphsar_api.rest.wishlist.respone.WishListResponse;
import com.kshrd.derphsar_api.service.implement.WishListServiceImp;
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
public class WishListRestController {

    private WishListServiceImp wishListServiceImp;
    private MessageProperties message;


    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setWishListServiceImp(WishListServiceImp wishListServiceImp) {
        this.wishListServiceImp = wishListServiceImp;
    }

    //create a wishlist
    @PostMapping("/wishlists")
    @ApiOperation(value = "create a wishlist", response = WishListRequestModel.class)
    public ResponseEntity<BaseApiResponse<WishListRequestModel>> createWishList(@RequestBody WishListRequestModel wishListRequestModel) {

        BaseApiResponse<WishListRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        WishListDto wishListDto = mapper.map(wishListRequestModel, WishListDto.class);

        UUID uuid = UUID.randomUUID();
        wishListDto.setWishlistId("DP" + uuid.toString().substring(0, 10));

        WishListDto result = wishListServiceImp.createWishList(wishListDto);
        WishListRequestModel requestModel = mapper.map(result, WishListRequestModel.class);

        response.setMessage("you have inserted a wishlist successfully!");
        response.setData(requestModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //get all wishlist
    @GetMapping("/wishlists")
    @ApiOperation(value = "show all wishlist", response = WishListRequestModel.class)
    public ResponseEntity<BaseApiResponse<List<WishListRequestModel>>> getWishLists() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<WishListRequestModel>> response =
                new BaseApiResponse<>();

        List<WishListDto> wishListDto = wishListServiceImp.getWishLists();
        List<WishListRequestModel> wishListRequestModels = new ArrayList<>();
        for (WishListDto wishList : wishListDto) {
            wishListRequestModels.add(mapper.map(wishList, WishListRequestModel.class));
        }
        response.setMessage("WishLists have been found succesfully");
        response.setData(wishListRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //delete a wishlist
    @DeleteMapping("/wishlists/{wishlist_id}")
    @ApiOperation(value = "delete a wishlist", response = Void.class)
    public ResponseEntity<BaseApiResponse<Void>> deleteWishList(@PathVariable("wishlist_id") String wishlist_id) {
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        wishListServiceImp.deleteWishList(wishlist_id);
        response.setMessage("you have deleted a wishlist successfully!");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    @GetMapping("wishlists/{userId}")
    @ApiOperation(value = "show all wishlist by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<WishListResponse>>> getAllWishlistByUserId(@PathVariable("userId") int userId) {

        BaseApiResponse<List<WishListResponse>> restApiMessage = new BaseApiResponse<>();
       //UserDto userDto = wishListServiceImp.getUserByUserId(userId);
        restApiMessage.setData(wishListServiceImp.test(userId));
        restApiMessage.setStatus(HttpStatus.FOUND);
        restApiMessage.setTime(new Timestamp(System.currentTimeMillis()));
        restApiMessage.setMessage("Select is successfully");
        return ResponseEntity.ok(restApiMessage);


    }
}