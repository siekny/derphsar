package com.example.derphsar_api.rest.wishlist.request;

import java.util.Date;

public class WishListRequestModel {

    private int id;
    private String wishlist_id;
    private Date fav_date;
    private int u_id;
    private int pro_id;

    public WishListRequestModel() {
    }

    public WishListRequestModel(int id, String wishlist_id, Date fav_date, int u_id, int pro_id) {
        this.id = id;
        this.wishlist_id = wishlist_id;
        this.fav_date = fav_date;
        this.u_id = u_id;
        this.pro_id = pro_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(String wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public Date getFav_date() {
        return fav_date;
    }

    public void setFav_date(Date fav_date) {
        this.fav_date = fav_date;
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

    @Override
    public String toString() {
        return "WishListDto{" +
                "id=" + id +
                ", wishlist_id='" + wishlist_id + '\'' +
                ", fav_date=" + fav_date +
                ", u_id=" + u_id +
                ", pro_id=" + pro_id +
                '}';
    }
}
