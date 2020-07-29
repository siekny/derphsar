package com.example.derphsar_api.repository.dto;

public class ProductDto {
    private int id;
    private String proId;
    private String name;
    private Double price;
    private String description;
    private boolean status;
    private boolean isSold;
    private int viewCount;

    private Object images;
    private Object details;

    private ShopDto shop;


    public ProductDto(){}

    public ProductDto(int id, String proId, String name, Double price, String description, boolean status, boolean isSold, int viewCount, Object images, Object details, ShopDto shop) {
        this.id = id;
        this.proId = proId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.isSold = isSold;
        this.viewCount = viewCount;
        this.images = images;
        this.details = details;
        this.shop = shop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public ShopDto getShop() {
        return shop;
    }

    public void setShop(ShopDto shop) {
        this.shop = shop;
    }


    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", proId='" + proId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", isSold=" + isSold +
                ", viewCount=" + viewCount +
                ", images=" + images +
                ", details=" + details +
                ", shop=" + shop +
                '}';
    }
}
