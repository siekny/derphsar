package com.kshrd.derphsar_api.rest.shop.response;

import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;

import java.util.Date;

public class ShopResponseModel {

    private String shopId;
    private String name;
    private String address;
    private String description;
    private String profile;
    private String cover;
    private boolean isOpen;
    private boolean status;
    private Date workingTime;
    private int u_id;
    private int cat_id;

    private UserDto userDto;
    private CategoryDto categoryDto;

    public ShopResponseModel() {
    }

    public ShopResponseModel(String shopId, String name, String address, String description, String profile, String cover, boolean isOpen, boolean status, Date workingTime, int u_id, int cat_id, UserDto userDto, CategoryDto categoryDto) {
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.isOpen = isOpen;
        this.status = status;
        this.workingTime = workingTime;
        this.u_id = u_id;
        this.cat_id = cat_id;
        this.userDto = userDto;
        this.categoryDto = categoryDto;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Date workingTime) {
        this.workingTime = workingTime;
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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    @Override
    public String toString() {
        return "ShopResponseModel{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", profile='" + profile + '\'' +
                ", cover='" + cover + '\'' +
                ", isOpen=" + isOpen +
                ", status=" + status +
                ", workingTime=" + workingTime +
                ", u_id=" + u_id +
                ", cat_id=" + cat_id +
                ", userDto=" + userDto +
                ", categoryDto=" + categoryDto +
                '}';
    }
}
