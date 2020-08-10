package com.kshrd.derphsar_api.rest.order.response;

import com.kshrd.derphsar_api.rest.shop.response.ShopOrderDetailResponse;
import com.kshrd.derphsar_api.rest.user.response.UserOrderDetailResponse;

public class OrderResponse {
    private String orderId;
//    private int user_id;
//    private int shop_id;

    private UserOrderDetailResponse user;
    private ShopOrderDetailResponse shop;


    public OrderResponse(){}

    public OrderResponse(String orderId, UserOrderDetailResponse user, ShopOrderDetailResponse shop) {
        this.orderId = orderId;
        this.user = user;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", user=" + user +
                ", shop=" + shop +
                '}';
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UserOrderDetailResponse getUser() {
        return user;
    }

    public void setUser(UserOrderDetailResponse user) {
        this.user = user;
    }

    public ShopOrderDetailResponse getShop() {
        return shop;
    }

    public void setShop(ShopOrderDetailResponse shop) {
        this.shop = shop;
    }
}
