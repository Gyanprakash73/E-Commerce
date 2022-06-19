package com.gyan.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalDataModel {

    @SerializedName("ID")
    @Expose
    String ID;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("final_price")
    @Expose
    String final_price;
    @SerializedName("qty")
    @Expose
    String qty;
    @SerializedName("img_product")
    @Expose
    String img_product;

    public LocalDataModel() {
    }

    public LocalDataModel(String ID, String name, String final_price, String qty, String img_product) {
        this.ID = ID;
        this.name = name;
        this.final_price = final_price;
        this.qty = qty;
        this.img_product = img_product;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }
}
