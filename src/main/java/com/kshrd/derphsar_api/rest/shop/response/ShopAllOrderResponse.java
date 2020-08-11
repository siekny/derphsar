package com.kshrd.derphsar_api.rest.shop.response;

public class ShopAllOrderResponse {
    private String shopId;
    private String name;

    public ShopAllOrderResponse(){}

    public ShopAllOrderResponse(String shopId, String name) {
        this.shopId = shopId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "ShopAllOrderResponse{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
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
}

