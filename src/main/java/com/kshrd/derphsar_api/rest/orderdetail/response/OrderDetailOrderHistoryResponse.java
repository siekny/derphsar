package com.kshrd.derphsar_api.rest.orderdetail.response;

import com.kshrd.derphsar_api.rest.product.response.ProducOrderHistorytResponse;

import java.sql.Date;

public class OrderDetailOrderHistoryResponse {
    private Date orderDate;
    private double quatity;
    private boolean checkoutStatus;
    private ProducOrderHistorytResponse product;

    public OrderDetailOrderHistoryResponse() {
    }

    public OrderDetailOrderHistoryResponse(Date orderDate, double quatity, boolean checkoutStatus, ProducOrderHistorytResponse product) {
        this.orderDate = orderDate;
        this.quatity = quatity;
        this.checkoutStatus = checkoutStatus;
        this.product = product;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getQuatity() {
        return quatity;
    }

    public void setQuatity(double quatity) {
        this.quatity = quatity;
    }

    public boolean isCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public ProducOrderHistorytResponse getProduct() {
        return product;
    }

    public void setProduct(ProducOrderHistorytResponse product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetailOrderHistoryResponse{" +
                "orderDate=" + orderDate +
                ", quatity=" + quatity +
                ", checkoutStatus=" + checkoutStatus +
                ", product=" + product +
                '}';
    }
}
