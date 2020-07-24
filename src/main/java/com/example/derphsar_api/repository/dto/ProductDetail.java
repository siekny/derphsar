package com.example.derphsar_api.repository.dto;

public class ProductDetail {
    private String color;
    private String size;

    private ProductDetail(){}

    public ProductDetail(String color, String size) {
        this.color = color;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
