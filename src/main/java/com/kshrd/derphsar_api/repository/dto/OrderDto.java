package com.kshrd.derphsar_api.repository.dto;

public class OrderDto {
    private int id;
    private String orderId;

    private int user_id;
    private int shop_id;

    private UserDto user;
    private ShopDto shop;

    public OrderDto(){}

    public OrderDto(int id, String orderId, int user_id, int shop_id, UserDto user, ShopDto shop) {
        this.id = id;
        this.orderId = orderId;
        this.user_id = user_id;
        this.shop_id = shop_id;
        this.user = user;
        this.shop = shop;
    }


    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", user_id=" + user_id +
                ", shop_id=" + shop_id +
                ", user=" + user +
                ", shop=" + shop +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }
}
