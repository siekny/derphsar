package com.kshrd.derphsar_api.rest.product.response;

import com.kshrd.derphsar_api.rest.shop.response.ShopOfAUserResponse;

public class ProductsOfAUserResponse {
    private String name;
    private int price;
    private int viewCount;
    private Object images;
    private ShopOfAUserResponse shop;

    public ProductsOfAUserResponse() {
    }

    public ProductsOfAUserResponse(String name, int price, int viewCount, Object images, ShopOfAUserResponse shop) {
        this.name = name;
        this.price = price;
        this.viewCount = viewCount;
        this.images = images;
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public ShopOfAUserResponse getShop() {
        return shop;
    }

    public void setShop(ShopOfAUserResponse shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ProductsOfAUserResponse{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", viewCount=" + viewCount +
                ", image=" + images +
                ", shop=" + shop +
                '}';
    }
}
