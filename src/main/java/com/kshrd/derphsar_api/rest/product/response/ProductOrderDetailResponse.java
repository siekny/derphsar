package com.kshrd.derphsar_api.rest.product.response;

public class ProductOrderDetailResponse {

    private String proId;
    private String name;
    private double price;
    private boolean soldStatus;

    public ProductOrderDetailResponse(){}

    public ProductOrderDetailResponse(String proId, String name, double price, boolean soldStatus) {
        this.proId = proId;
        this.name = name;
        this.price = price;
        this.soldStatus = soldStatus;
    }

    @Override
    public String toString() {
        return "ProductOrderDetailResponse{" +
                "proId=" + proId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", soldStatus=" + soldStatus +
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
    }
}
