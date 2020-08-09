package com.kshrd.derphsar_api.rest.category.response;

public class CategoryResponseModel {
    private String catId;
    private String name;

    public CategoryResponseModel(){}

    public CategoryResponseModel(String catId, String name) {
        this.catId = catId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "CategoryResponseModel{" +
                "catId='" + catId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
