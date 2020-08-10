package com.kshrd.derphsar_api.rest.product.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.derphsar_api.repository.dto.ShopDto;

public class ProductResponseModel {

    @JsonIgnore
    private String id;
    private String name;
    private Double price;
    private String description;
    private boolean status;
    private boolean isSold;
    private int viewCount;

    private String imageName;
    private String imageUrl;

    private String color;
    private String size;

    private Object images;
    private Object details;

    private ShopDto shop;


    public ProductResponseModel(){}

    public ProductResponseModel(String id, String name, Double price, String description, boolean status, boolean isSold, int viewCount, String imageName, String imageUrl, String color, String size, Object images, Object details, ShopDto shop) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.isSold = isSold;
        this.viewCount = viewCount;
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.color = color;
        this.size = size;
        this.images = images;
        this.details = details;
        this.shop = shop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        this.isSold = sold;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }


    @Override
    public String toString() {
        return "ProductResponseModel{" +
                "proId='" + id + '\'' +
                ", proName='" + name + '\'' +
                ", proPrice=" + price +
                ", proDescription='" + description + '\'' +
                ", proStatus=" + status +
                ", proIsSold=" + isSold +
                ", proViewCount=" + viewCount +
                ", proImageName='" + imageName + '\'' +
                ", proImageUrl='" + imageUrl + '\'' +
                ", procolor='" + color + '\'' +
                ", proSize='" + size + '\'' +
                ", proImages=" + images +
                ", proDetails=" + details +
                ", shop=" + shop +
                '}';
    }
}
