package com.example.derphsar_api.rest.wishlist.request;

import java.util.Date;

public class WishListRequestModel {

    private String wishlistId;
    private Date favDate;
    private int u_id;
    private int pro_id;

    public WishListRequestModel() {
    }

    public WishListRequestModel(String wishlistId, Date favDate, int u_id, int pro_id) {
        this.wishlistId = wishlistId;
        this.favDate = favDate;
        this.u_id = u_id;
        this.pro_id = pro_id;
    }

    @Override
    public String toString() {
        return "WishListRequestModel{" +
                "wishlistId='" + wishlistId + '\'' +
                ", favDate=" + favDate +
                ", u_id=" + u_id +
                ", pro_id=" + pro_id +
                '}';
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

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }
}
