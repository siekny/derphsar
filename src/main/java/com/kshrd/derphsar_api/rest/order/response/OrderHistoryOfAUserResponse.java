package com.kshrd.derphsar_api.rest.order.response;

import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailOrderHistoryResponse;
import com.kshrd.derphsar_api.rest.product.response.ProducOrderHistorytResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductResponseModel;
import com.kshrd.derphsar_api.rest.shop.response.ShopOrderHistoryResponse;

public class OrderHistoryOfAUserResponse {
    private OrderDetailOrderHistoryResponse orderDetail;
    private ShopOrderHistoryResponse shop;

    public OrderHistoryOfAUserResponse() {
    }

    public OrderHistoryOfAUserResponse(OrderDetailOrderHistoryResponse orderDetail, ShopOrderHistoryResponse shop) {
        this.orderDetail = orderDetail;
        this.shop = shop;
    }

    public OrderDetailOrderHistoryResponse getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailOrderHistoryResponse order) {
        this.orderDetail = order;
    }

    public ShopOrderHistoryResponse getShop() {
        return shop;
    }

    public void setShop(ShopOrderHistoryResponse shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "OrderHistoryOfAUserResponse{" +
                "orderDetail=" + orderDetail +
                ", shop=" + shop +
                '}';
    }
}
