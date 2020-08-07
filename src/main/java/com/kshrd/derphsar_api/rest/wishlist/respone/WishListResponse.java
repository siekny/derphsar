package com.kshrd.derphsar_api.rest.wishlist.respone;

import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;

import java.util.Date;

public class WishListResponse {

    private int id;
    private String wishlistId;
    private Date favDate;
    private boolean status;

    private UserDto user;
    private ProductDto product;

    public WishListResponse(){}

    public WishListResponse(int id, String wishlistId, Date favDate, boolean status, UserDto user, ProductDto product) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.favDate = favDate;
        this.status = status;
        this.user = user;
        this.product = product;
    }

    @Override
    public String toString() {
        return "WishListResponse{" +
                "id=" + id +
                ", wishlistId='" + wishlistId + '\'' +
                ", favDate=" + favDate +
                ", status=" + status +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Date getFavDate() {
        return favDate;
    }

    public void setFavDate(Date favDate) {
        this.favDate = favDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
