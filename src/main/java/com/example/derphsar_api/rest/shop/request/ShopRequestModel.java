package com.example.derphsar_api.rest.shop.request;

import java.util.Date;

public class ShopRequestModel {

    private String shop_name;
    private String shop_adress;
    private String shop_description;
    private String shop_profile;
    private String shop_cover;
    private boolean shop_status;
    private boolean status;
    private Date working_time;

    public ShopRequestModel() {}

    public ShopRequestModel(String shop_name, String shop_adress, String shop_description, String shop_profile, String shop_cover, boolean shop_status, boolean status, Date working_time) {
        this.shop_name = shop_name;
        this.shop_adress = shop_adress;
        this.shop_description = shop_description;
        this.shop_profile = shop_profile;
        this.shop_cover = shop_cover;
        this.shop_status = shop_status;
        this.status = status;
        this.working_time = working_time;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_adress() {
        return shop_adress;
    }

    public void setShop_adress(String shop_adress) {
        this.shop_adress = shop_adress;
    }

    public String getShop_description() {
        return shop_description;
    }

    public void setShop_description(String shop_description) {
        this.shop_description = shop_description;
    }

    public String getShop_profile() {
        return shop_profile;
    }

    public void setShop_profile(String shop_profile) {
        this.shop_profile = shop_profile;
    }

    public String getShop_cover() {
        return shop_cover;
    }

    public void setShop_cover(String shop_cover) {
        this.shop_cover = shop_cover;
    }

    public boolean isShop_status() {
        return shop_status;
    }

    public void setShop_status(boolean shop_status) {
        this.shop_status = shop_status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getWorking_time() {
        return working_time;
    }

    public void setWorking_time(Date working_time) {
        this.working_time = working_time;
    }

    @Override
    public String toString() {
        return "ArticleRequestModel{" +
                "shop_name='" + shop_name + '\'' +
                ", shop_adress='" + shop_adress + '\'' +
                ", shop_description='" + shop_description + '\'' +
                ", shop_profile='" + shop_profile + '\'' +
                ", shop_cover='" + shop_cover + '\'' +
                ", shop_status=" + shop_status +
                ", status=" + status +
                ", working_time=" + working_time +
                '}';
    }
}
