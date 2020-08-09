package com.kshrd.derphsar_api.rest.shop.response;

import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.category.response.CategoryResponseModel;
import com.kshrd.derphsar_api.rest.promotion.request.PromotionRequestModel;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;

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

    private UserResponseModel user;
    private CategoryResponseModel category;
    private PromotionResponseModel promotion;

    public ShopResponseModel() {
    }

    public ShopResponseModel(String shopId, String name, String address, String description, String profile, String cover, boolean isOpen, boolean status, Date workingTime, UserResponseModel user, CategoryResponseModel category, PromotionResponseModel promotion) {
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.isOpen = isOpen;
        this.status = status;
        this.workingTime = workingTime;
        this.user = user;
        this.category = category;
        this.promotion = promotion;
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
                ", user=" + user +
                ", category=" + category +
                ", promotion=" + promotion +
                '}';
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

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    public CategoryResponseModel getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseModel category) {
        this.category = category;
    }

    public PromotionResponseModel getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionResponseModel promotion) {
        this.promotion = promotion;
    }
}
