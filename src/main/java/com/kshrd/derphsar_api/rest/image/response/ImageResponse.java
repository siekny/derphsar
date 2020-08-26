package com.kshrd.derphsar_api.rest.image.response;

public class ImageResponse {

    private String imageUrl;

    public ImageResponse(){}

    public ImageResponse(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
