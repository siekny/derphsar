package com.kshrd.derphsar_api.repository.dto;


import java.sql.Date;

public class ProductDto {

    private int id;
    private String proId;
    private String name;
    private Double price;
    private String description;
    private boolean status;
    private boolean soldStatus;
    private int viewCount;
    private Date postDate;

    private Object images;
    private Object details;

    private ShopDto shop;
    private PromotionDto promotion;

    public ProductDto(){}

    public ProductDto(int id, String proId, String name, Date postDate, Double price, String description, boolean status, boolean isSold, int viewCount, Object images, Object details, ShopDto shop, PromotionDto promotion) {
        this.id = id;
        this.proId = proId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.soldStatus = isSold;
        this.viewCount = viewCount;
        this.images = images;
        this.details = details;
        this.shop = shop;
        this.postDate = postDate;
        this.promotion = promotion;
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
                ", soldStatus=" + soldStatus +
                ", viewCount=" + viewCount +
                ", postDate=" + postDate +
                ", images=" + images +
                ", details=" + details +
                ", shop=" + shop +
                ", promotion=" + promotion +
                '}';
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
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

    public PromotionDto getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionDto promotion) {
        this.promotion = promotion;
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

    public boolean isSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
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


}
