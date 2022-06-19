package com.gyan.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {
    @SerializedName("ID")
    @Expose
    String ID;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("category_name")
    @Expose
    String category_name;
    @SerializedName("final_price")
    @Expose
    String final_price;
    @SerializedName("actual_price")
    @Expose
    String actual_price;
    @SerializedName("discount")
    @Expose
    String discount;
    @SerializedName("qty")
    @Expose
    String qty;
    @SerializedName("images")
    @Expose
    List<Images> images = null;
    @SerializedName("variant")
    @Expose
    List<Variant> variant = null;
    @SerializedName("img_product")
    @Expose
    String img_product;

    public Products() {
    }

    public Products(String ID, String name, String category_name, String final_price, String actual_price, String discount, String qty, List<Images> images, List<Variant> variant) {
        this.ID = ID;
        this.name = name;
        this.category_name = category_name;
        this.final_price = final_price;
        this.actual_price = actual_price;
        this.discount = discount;
        this.qty = qty;
        this.images = images;
        this.variant = variant;
    }

    public Products(String img_product, String ID, String name, String final_price, String actual_price, String discount, String qty) {
        this.img_product=img_product;
        this.ID=ID;
        this.name=name;
        this.final_price=final_price;
        this.actual_price=actual_price;
        this.discount=discount;
        this.qty=qty;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getFinal_price() {
        return final_price;
    }

    public String getActual_price() {
        return actual_price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getQty() {
        return qty;
    }

    public List<Images> getImages() {
        return images;
    }

    public List<Variant> getVariant() {
        return variant;
    }

    public String getImg_product() {
        return img_product;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public void setActual_price(String actual_price) {
        this.actual_price = actual_price;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public void setVariant(List<Variant> variant) {
        this.variant = variant;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }
}
