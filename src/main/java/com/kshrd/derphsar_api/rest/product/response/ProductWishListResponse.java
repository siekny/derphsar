package com.kshrd.derphsar_api.rest.product.response;

import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.rest.shop.response.ShopWishListResponse;

public class ProductWishListResponse {
    private String proId;
    private String name;
    private Object image;
    private Double price;

    private ShopWishListResponse shop;

    public ProductWishListResponse(){

    }

    public ProductWishListResponse(String proId, String name, Object image, Double price, ShopWishListResponse shop) {
        this.proId = proId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ProductWishListResponse{" +
                "proId='" + proId + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", shop=" + shop +
                '}';
    }


    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShopWishListResponse getShop() {
        return shop;
    }

    public void setShop(ShopWishListResponse shop) {
        this.shop = shop;
    }
}
