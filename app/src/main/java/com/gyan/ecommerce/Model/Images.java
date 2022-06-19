package com.gyan.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("img_id")
    @Expose
    Long img_id;
    @SerializedName("img_product")
    @Expose
    String img_product;

    public Images() {
    }

    public Images(Long img_id, String img_product) {
        this.img_id = img_id;
        this.img_product = img_product;
    }

    public Long getImg_id() {
        return img_id;
    }

    public String getImg_product() {
        return img_product;
    }
}
