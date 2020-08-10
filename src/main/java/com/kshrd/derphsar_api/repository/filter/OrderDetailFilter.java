package com.kshrd.derphsar_api.repository.filter;

public class OrderDetailFilter {
    private Integer orderId;
    private Integer userId;

    public OrderDetailFilter(){}

    public OrderDetailFilter(Integer orderId, Integer userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderDetailFilter{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                '}';
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
