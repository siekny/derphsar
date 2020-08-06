package com.example.derphsar_api.rest.promotion.request;

import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.repository.dto.ShopDto;

import java.sql.Date;

public class PromotionRequestModel {

    private String promoId;
    private String title;
    private boolean isApply;
    private Date startDate;
    private Date endDate;
    private boolean status;
    private double rank;
    private int pro_id;

    public PromotionRequestModel(){}

    public PromotionRequestModel(String promoId, String title, boolean isApply, Date startDate, Date endDate, boolean status, double rank, int pro_id) {
        this.promoId = promoId;
        this.title = title;
        this.isApply = isApply;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.rank = rank;
        this.pro_id = pro_id;
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isApply() {
        return isApply;
    }

    public void setApply(boolean apply) {
        isApply = apply;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    @Override
    public String toString() {
        return "PromotionRequestModel{" +
                "promoId='" + promoId + '\'' +
                ", title='" + title + '\'' +
                ", isApply=" + isApply +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", rank=" + rank +
                ", pro_id=" + pro_id +
                '}';
    }
}
