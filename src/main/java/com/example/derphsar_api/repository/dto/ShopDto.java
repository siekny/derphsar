package com.example.derphsar_api.repository.dto;

public class ShopDto {
    private int id;
    private String shopId;
    private String shopName;
    private String shopAddress;

    public ShopDto(){}

    public ShopDto(int id, String shopId, String shopName, String shopAddress) {
        this.id = id;
        this.shopId = shopId;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    @Override
    public String toString() {
        return "ShopDto{" +
                "id=" + id +
                ", shopId='" + shopId + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                '}';
    }
}
