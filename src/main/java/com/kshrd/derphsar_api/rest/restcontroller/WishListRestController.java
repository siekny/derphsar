package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.WishListDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.wishlist.request.WishListRequestModel;
import com.kshrd.derphsar_api.rest.wishlist.response.WishListResponse;
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





    /**
     * Post wishlists
     *
     * @param wishListRequestModel - Wishlist request model
     * @return - Return response message
     */
    @PostMapping("/wishlists")
    @ApiOperation(value = "create a wishlist", response = WishListResponse.class)
    public ResponseEntity<BaseApiResponse<WishListResponse>> createWishList(@RequestBody WishListRequestModel wishListRequestModel) {
        BaseApiResponse<WishListResponse> response = new BaseApiResponse<>();

        ModelMapper mapper = new ModelMapper();
        WishListDto wishListDto = mapper.map(wishListRequestModel, WishListDto.class);

        UUID uuid = UUID.randomUUID();
        wishListDto.setWishlistId("DP" + uuid.toString().substring(0, 10));
        wishListDto.setFavDate(new Timestamp(System.currentTimeMillis()));
        wishListDto.setStatus(true);

        WishListDto result = wishListServiceImp.createWishList(wishListDto);
        WishListResponse wishListResponse = mapper.map(result, WishListResponse.class);

        response.setMessage(message.inserted("Wishlist"));
        response.setData(wishListResponse);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Get wishlists
     *
     * @return - Return response message
     */
    @GetMapping("/wishlists")
    @ApiOperation(value = "show all wishlist", response = WishListResponse.class)
    public ResponseEntity<BaseApiResponse<List<WishListResponse>>> getWishLists() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<WishListResponse>> response =
                new BaseApiResponse<>();

        List<WishListDto> wishListDto = wishListServiceImp.getWishLists();
        List<WishListResponse> wishListRequestModels = new ArrayList<>();
        for (WishListDto wishList : wishListDto) {
            wishListRequestModels.add(mapper.map(wishList, WishListResponse.class));
        }
        response.setMessage("WishLists have been found succesfully");
        response.setData(wishListRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Delete a wishlist
     *
     * @param wishlist_id - Id of a wishlist
     * @return - Return response message
     */
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


    /**
     * Get wishlists
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("wishlists/{userId}")
    @ApiOperation(value = "show all wishlist by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<WishListResponse>>> getAllWishlistByUserId(@PathVariable("userId") int userId,
                                                                                          @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                          @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                          @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                          @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();

        pagination.setTotalCount(wishListServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<WishListResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        List<WishListDto> wishListDtos = wishListServiceImp.getAllWishListByUserId(userId,pagination);

        List<WishListResponse> wishListResponses = new ArrayList<>();

        for (WishListDto wishListDto : wishListDtos) {
            WishListResponse wishListResponse = mapper.map(wishListDto, WishListResponse.class);
            wishListResponses.add(wishListResponse);
        }

        response.setPagination(pagination);
        response.setData(wishListResponses);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setMessage(message.selected("WishLists"));
        System.out.println("Wishlist = " + response);

        return ResponseEntity.ok(response);
    }
}