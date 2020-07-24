package com.example.derphsar_api.rest.product.request;

import com.example.derphsar_api.repository.dto.ShopDto;

public class ProductRequestModel {

    private String proId;
    private String proName;
    private Double proPrice;
    private String proDescription;
    private boolean proStatus;
    private boolean proIsSold;
    private int proViewCount;

//    private String proImageName;
//    private String proImageUrl;
//
//    private String procolor;
//    private String proSize;

    private Object proImages;
    private Object proDetails;

    private ShopDto shop;

    public ProductRequestModel(){}

    public ProductRequestModel(String proId, String proName, Double proPrice, String proDescription, boolean proStatus, boolean proIsSold, int proViewCount, Object proImages, Object proDetails, ShopDto shop) {
        this.proId = proId;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proDescription = proDescription;
        this.proStatus = proStatus;
        this.proIsSold = proIsSold;
        this.proViewCount = proViewCount;
        this.proImages = proImages;
        this.proDetails = proDetails;
        this.shop = shop;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Double getProPrice() {
        return proPrice;
    }

    public void setProPrice(Double proPrice) {
        this.proPrice = proPrice;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public boolean isProStatus() {
        return proStatus;
    }

    public void setProStatus(boolean proStatus) {
        this.proStatus = proStatus;
    }

    public boolean isProIsSold() {
        return proIsSold;
    }

    public void setProIsSold(boolean proIsSold) {
        this.proIsSold = proIsSold;
    }

    public int getProViewCount() {
        return proViewCount;
    }

    public void setProViewCount(int proViewCount) {
        this.proViewCount = proViewCount;
    }

    public Object getProImages() {
        return proImages;
    }

    public void setProImages(Object proImages) {
        this.proImages = proImages;
    }

    public Object getProDetails() {
        return proDetails;
    }

    public void setProDetails(Object proDetails) {
        this.proDetails = proDetails;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }


    @Override
    public String toString() {
        return "ProductRequestModel{" +
                "proId='" + proId + '\'' +
                ", proName='" + proName + '\'' +
                ", proPrice=" + proPrice +
                ", proDescription='" + proDescription + '\'' +
                ", proStatus=" + proStatus +
                ", proIsSold=" + proIsSold +
                ", proViewCount=" + proViewCount +
                ", proImages=" + proImages +
                ", proDetails=" + proDetails +
                ", shop=" + shop +
                '}';
    }
}
