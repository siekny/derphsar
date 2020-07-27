package com.example.derphsar_api.repository.dto;

import java.util.Date;

public class ShopDto {
//    private int id;
//    private String shopId;
//    private String shopName;
//    private String shopAddress;
//
//    public ShopDto(){}
//
//    public ShopDto(int id, String shopId, String shopName, String shopAddress) {
//        this.id = id;
//        this.shopId = shopId;
//        this.shopName = shopName;
//        this.shopAddress = shopAddress;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getShopId() {
//        return shopId;
//    }
//
//    public void setShopId(String shopId) {
//        this.shopId = shopId;
//    }
//
//    public String getShopName() {
//        return shopName;
//    }
//
//    public void setShopName(String shopName) {
//        this.shopName = shopName;
//    }
//
//    public String getShopAddress() {
//        return shopAddress;
//    }
//
//    public void setShopAddress(String shopAddress) {
//        this.shopAddress = shopAddress;
//    }
//
//    @Override
//    public String toString() {
//        return "ShopDto{" +
//                "id=" + id +
//                ", shopId='" + shopId + '\'' +
//                ", shopName='" + shopName + '\'' +
//                ", shopAddress='" + shopAddress + '\'' +
//                '}';
//    }

    private int id;
    private String shop_id;
    private String shop_name;
    private String shop_adress;
    private String shop_description;
    private String shop_profile;
    private String shop_cover;
    private boolean shop_status;
    private boolean status;
    private Date working_time;
    private int u_id;
    private int cat_id;

    public ShopDto() {
    }

    public ShopDto(int id, String shop_id, String shop_name, String shop_adress, String shop_description, String shop_profile, String shop_cover, boolean shop_status, boolean status, Date working_time, int u_id, int cat_id) {
        this.id = id;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.shop_adress = shop_adress;
        this.shop_description = shop_description;
        this.shop_profile = shop_profile;
        this.shop_cover = shop_cover;
        this.shop_status = shop_status;
        this.status = status;
        this.working_time = working_time;
        this.u_id = u_id;
        this.cat_id = cat_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
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

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    @Override
    public String toString() {
        return "ShopDto{" +
                "id=" + id +
                ", shop_id='" + shop_id + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", shop_adress='" + shop_adress + '\'' +
                ", shop_description='" + shop_description + '\'' +
                ", shop_profile='" + shop_profile + '\'' +
                ", shop_cover='" + shop_cover + '\'' +
                ", shop_status=" + shop_status +
                ", status=" + status +
                ", working_time=" + working_time +
                ", u_id=" + u_id +
                ", cat_id=" + cat_id +
                '}';
    }
}
