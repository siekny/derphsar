package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.wishlist.request.WishListRequestModel;
import com.kshrd.derphsar_api.service.implement.WishListServiceImp;
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

    @Autowired
    public void setWishListServiceImp(WishListServiceImp wishListServiceImp) {
        this.wishListServiceImp = wishListServiceImp;
    }

    //create a wishlist
    @PostMapping("/wishlists")
    public ResponseEntity<BaseApiResponse<WishListRequestModel>> createWishList(@RequestBody WishListRequestModel wishListRequestModel){

        BaseApiResponse<WishListRequestModel> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        WishListDto wishListDto = mapper.map(wishListRequestModel, WishListDto.class);

        UUID uuid = UUID.randomUUID();
        wishListDto.setWishlistId("DP"+uuid.toString().substring(0,10));

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
    public ResponseEntity<BaseApiResponse<List<WishListRequestModel>>> getWishLists() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<WishListRequestModel>> response =
                new BaseApiResponse<>();

        List<WishListDto> wishListDto = wishListServiceImp.getWishLists();
        List<WishListRequestModel> wishListRequestModels = new ArrayList<>();
        for (WishListDto wishList : wishListDto) {
            wishListRequestModels.add(mapper.map(wishList, WishListRequestModel.class));
        }
        response.setMessage("you have selected all wishlists successfully!");
        response.setData(wishListRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //delete a wishlist
    @DeleteMapping("/wishlists/{wishlist_id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteWishList(@PathVariable("wishlist_id") String wishlist_id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        wishListServiceImp.deleteWishList(wishlist_id);
        response.setMessage("you have deleted a wishlist successfully!");
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }

}
