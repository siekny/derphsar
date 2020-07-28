package com.example.derphsar_api.rest.shop.request;

import java.util.Date;

public class ShopRequestModel {

    private int id;
    private String shop_id;
    private String name;
    private String address;
    private String description;
    private String profile;
    private String cover;
    private boolean is_open;
    private boolean status;
    private Date working_time;
    private int u_id;
    private int cat_id;

    public ShopRequestModel() {}

    public ShopRequestModel(int id, String shop_id, String name, String address, String description, String profile, String cover, boolean is_open, boolean status, Date working_time, int u_id, int cat_id) {
        this.id = id;
        this.shop_id = shop_id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.is_open = is_open;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
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
        return "ShopRequestModel{" +
                "id=" + id +
                ", shop_id='" + shop_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", profile='" + profile + '\'' +
                ", cover='" + cover + '\'' +
                ", is_open=" + is_open +
                ", status=" + status +
                ", working_time=" + working_time +
                ", u_id=" + u_id +
                ", cat_id=" + cat_id +
                '}';
    }
}
