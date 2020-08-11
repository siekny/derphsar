package com.kshrd.derphsar_api.rest.order.response;

import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserByShopResponse;

public class OrderByShopResponse {

    private String orderId;
    private UserByShopResponse user;
    private ShopResponseModel shop;
    private OrderDetailResponse orderDetail;

    public OrderByShopResponse(){}

    public OrderByShopResponse(String orderId, UserByShopResponse user, ShopResponseModel shop, OrderDetailResponse orderDetail) {
        this.orderId = orderId;
        this.user = user;
        this.shop = shop;
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OrderByShopResponse{" +
                "orderId='" + orderId + '\'' +
                ", user=" + user +
                ", shop=" + shop +
                ", orderDetail=" + orderDetail +
                '}';
    }



}
