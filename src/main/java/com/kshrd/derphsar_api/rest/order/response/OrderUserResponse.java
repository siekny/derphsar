package com.kshrd.derphsar_api.rest.order.response;

public class OrderUserResponse {

    private String orderId;
    private int userId;

    public OrderUserResponse(){}

    public OrderUserResponse(String orderId, int userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderUserResponse{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
