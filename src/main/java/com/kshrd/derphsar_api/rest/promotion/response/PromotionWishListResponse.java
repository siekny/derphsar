package com.kshrd.derphsar_api.rest.promotion.response;

public class PromotionWishListResponse {

    private String promoId;
    private String name;
    private String startRank;
    private String endRank;

    public PromotionWishListResponse(){}

    public PromotionWishListResponse(String promoId, String name, String startRank, String endRank) {
        this.promoId = promoId;
        this.name = name;
        this.startRank = startRank;
        this.endRank = endRank;
    }


    @Override
    public String toString() {
        return "PromotionWishListResponse{" +
                "promoId='" + promoId + '\'' +
                ", name='" + name + '\'' +
                ", startRank='" + startRank + '\'' +
                ", endRank='" + endRank + '\'' +
                '}';
    }


    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartRank() {
        return startRank;
    }

    public void setStartRank(String startRank) {
        this.startRank = startRank;
    }

    public String getEndRank() {
        return endRank;
    }

    public void setEndRank(String endRank) {
        this.endRank = endRank;
    }
}
