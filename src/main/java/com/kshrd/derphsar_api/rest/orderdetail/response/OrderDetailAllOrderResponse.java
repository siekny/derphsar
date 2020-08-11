package com.kshrd.derphsar_api.rest.orderdetail.response;

import java.sql.Date;

public class OrderDetailAllOrderResponse {

    private boolean checkoutStatus;
    private boolean status;
    private Date orderDate;


    public OrderDetailAllOrderResponse(){}

    public OrderDetailAllOrderResponse(boolean checkoutStatus, boolean status, Date orderDate) {
        this.checkoutStatus = checkoutStatus;
        this.status = status;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderDetailAllOrderResponse{" +
                "checkoutStatus=" + checkoutStatus +
                ", status=" + status +
                ", orderDate=" + orderDate +
                '}';
    }


    public boolean isCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
