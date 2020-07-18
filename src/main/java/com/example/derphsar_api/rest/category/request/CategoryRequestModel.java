package com.example.derphsar_api.rest.category.request;

public class CategoryRequestModel {
    private String cat_id;
    private String cat_name;

    public CategoryRequestModel(){}

    public CategoryRequestModel(String cat_id, String cat_name) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    @Override
    public String toString() {
        return "CategoryRequestModel{" +
                "cat_id=" + cat_id +
                ", cat_name='" + cat_name + '\'' +
                '}';
    }
}
